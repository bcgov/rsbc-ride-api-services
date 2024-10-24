#!/usr/bin/env python3

import os
import io
import sys
import asyncio
import logging
from typing import List
from contextlib import asynccontextmanager
from src.ftputil import FTPUtil
from src.reconFileProcessor import ReconFileProcessor

# Set up logging
numeric_level = getattr(logging, os.getenv('LOG_LEVEL', 'INFO').upper(), 10)
logging.basicConfig(
    level=numeric_level,
    format='%(asctime)s %(levelname)s %(module)s:%(lineno)d [RIDE_PRIMEADAPTER]: %(message)s'
)
logger = logging.getLogger(__name__)

class ProcessingError(Exception):
    """Custom exception for processing errors"""
    pass

@asynccontextmanager
async def async_sftp_open(sftp, path):
    """Async context manager for reading SFTP files"""
    file_obj = sftp.open(path, 'r')
    try:
        yield file_obj
    finally:
        file_obj.close()

async def read_sftp_file(sftp, file_path: str) -> io.BytesIO:
    """Read SFTP file content asynchronously"""
    async with async_sftp_open(sftp, file_path) as f:
        content = f.read()
        return io.BytesIO(content)

def ensure_folder_exists(sftp, folder_path: str) -> None:
    """Ensure the specified folder exists in SFTP"""
    try:
        sftp.stat(folder_path)
    except IOError:
        logger.info(f"Creating folder: {folder_path}")
        sftp.mkdir(folder_path)

def handle_failed_file(sftp, source_folder: str, file_name: str) -> str:
    """
    Rename failed file by appending _error to its name
    Returns the new filename
    """
    file_name_without_ext, file_extension = os.path.splitext(file_name)
    new_file_name = f"{file_name_without_ext}_error{file_extension}"
    old_path = f'/{source_folder}/{file_name}'
    new_path = f'/{source_folder}/{new_file_name}'
    
    try:
        sftp.rename(old_path, new_path)
        logger.info(f"Renamed failed file from {file_name} to {new_file_name}")
        return new_file_name
    except IOError as e:
        logger.error(f"Failed to rename error file {file_name}: {str(e)}")
        raise

def archive_processed_files(sftp, source_folder: str, archive_folder: str, files: List[str]) -> List[str]:
    """Archive successfully processed files"""
    archived_files = []
    for file in files:
        source_path = f'/{source_folder}/{file}'
        file_name, file_extension = os.path.splitext(file)
        new_file_name = f"{file_name}_processed{file_extension}"
        dest_path = f'/{archive_folder}/{new_file_name}'
        
        try:
            # Remove existing archived file if it exists
            try:
                sftp.stat(dest_path)
                logger.info(f"File {new_file_name} already exists in archive folder. Removing it.")
                sftp.remove(dest_path)
            except IOError:
                pass

            sftp.rename(source_path, dest_path)
            logger.info(f"Moved and renamed {file} to {new_file_name} in archive folder")
            archived_files.append(new_file_name)
        except IOError as e:
            logger.error(f"Failed to archive {file}: {str(e)}")
            raise ProcessingError(f"Failed to archive {file}: {str(e)}")
    return archived_files

async def process_ftp_files() -> bool:
    """
    Main function to process FTP files
    Returns True if successful, False if failed
    """
    # Load environment variables
    host = os.getenv('PRIME_SFTP_SERVER')
    port = int(os.getenv('PRIME_SFTP_SERVER_PORT', '22'))
    user = os.getenv('PRIME_SFTP_USER')
    user_password = os.getenv('PRIME_SFTP_PASS')
    priv_key_str = os.getenv('PRIME_SFTP_PRIV_KEY_FILE')
    priv_key_file_passphrase = os.getenv('PRIME_SFTP_PRIV_KEY_FILE_PASSPHRASE', None)
    pub_key_str = os.getenv('PRIME_SFTP_PUB_KEY_FILE')
    primerecon_folder = os.getenv('PRIMERECON_FTP_FOLDER', 'primerecon')
    primerecon_archive_folder = os.getenv('PRIMERECON_ARCHIVE_FOLDER', 'primerecon_archive')
    incoming_endpoint = os.getenv('PRIME_ADAPTER_RECON_INCOMING_ENDPOINT', 'http://localhost:8080/primeadapter/v3/api/recon/incoming')
    outgoing_endpoint = os.getenv('PRIME_ADAPTER_RECON_OUTGOING_ENDPOINT', 'http://localhost:8080/primeadapter/v3/api/recon/outgoing')

    ftputil = None
    try:
        # Initialize FTP connection
        ftputil = FTPUtil(
            host=host,
            port=port,
            user=user,
            user_password=user_password,
            priv_key_str=priv_key_str,
            pub_key_str=pub_key_str,
            passphrase=priv_key_file_passphrase,
            known_hosts=None
        )
        sftp = ftputil.acquire_sftp_channel()

        # Ensure folders exist
        ensure_folder_exists(sftp, f'/{primerecon_folder}')
        ensure_folder_exists(sftp, f'/{primerecon_archive_folder}')

        # Get list of txt files (excluding error files)
        files = sftp.listdir(f'/{primerecon_folder}')
        txt_files = [f for f in files if f.endswith('.txt') and not f.endswith('_error.txt')]
        
        if not txt_files:
            logger.info("No files to process")
            return True

        recon_processor = ReconFileProcessor(incoming_endpoint, outgoing_endpoint)
        processed_files = []
        failed_files = []

        # Process files concurrently
        async def process_single_file(file: str):
            try:
                file_path = f'/{primerecon_folder}/{file}'
                content = await read_sftp_file(sftp, file_path)
                result = await recon_processor.process_file(file, content)
                
                if result.is_success:
                    processed_files.append(file)
                    logger.info(f"Successfully processed {file}: {result.message}")
                else:
                    failed_files.append(file)
                    logger.error(f"Failed to process {file}: {result.message}")
                    logger.error("Errors: " + "\n".join(result.errors))
                    new_error_file = handle_failed_file(sftp, primerecon_folder, file)
                    
            except Exception as e:
                logger.error(f"Failed to process file {file}: {str(e)}")
                failed_files.append(file)
                new_error_file = handle_failed_file(sftp, primerecon_folder, file)

        # Process all files concurrently
        await asyncio.gather(*[process_single_file(file) for file in txt_files])

        # Archive successfully processed files
        if processed_files:
            archived_files = archive_processed_files(sftp, primerecon_folder, primerecon_archive_folder, processed_files)
            logger.info(f"Successfully processed and archived {len(archived_files)} files")

        if failed_files:
            logger.error(f"Failed to process {len(failed_files)} files: {', '.join(failed_files)}")
            return False

        return True

    except Exception as e:
        logger.error(f"Critical error in file processing: {str(e)}")
        return False
    finally:
        if ftputil:
            ftputil.release_sftp_channel()

async def main():
    """Main entry point for the script"""
    success = await process_ftp_files()
    sys.exit(0 if success else 1)

if __name__ == "__main__":
    asyncio.run(main())