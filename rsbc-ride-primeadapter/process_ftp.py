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
    
    
def get_full_path(*parts: str) -> str:
    """
    Construct a clean path from parts, removing duplicate slashes and leading/trailing slashes
    """
    # Join all parts with '/', remove duplicate slashes, and strip leading/trailing slashes
    return '/'.join(p.strip('/') for p in parts if p).strip('/')

def ensure_folder_exists(sftp, folder_path: str) -> None:
    """Ensure the specified folder exists in SFTP"""
    try:
        logger.debug(f"Checking if folder exists: {folder_path}")
        sftp.stat(folder_path)
        logger.info(f"Folder exists: {folder_path}")
    except IOError:
        logger.info(f"Creating folder: {folder_path}")
        try:
            sftp.mkdir(folder_path)
        except IOError as e:
            # If parent directory doesn't exist, try to create it
            parent_dir = os.path.dirname(folder_path)
            if parent_dir:
                logger.info(f"Parent folder doesn't exist, creating: {parent_dir}")
                try:
                    sftp.stat(parent_dir)
                except IOError:
                    sftp.mkdir(parent_dir)
                # Try creating the original folder again
                sftp.mkdir(folder_path)

def handle_failed_file(sftp, source_folder: str, file_name: str) -> str:
    """Rename failed file by appending _error to its name"""
    file_name_without_ext, file_extension = os.path.splitext(file_name)
    new_file_name = f"{file_name_without_ext}_error{file_extension}"
    old_path = get_full_path(source_folder, file_name)
    new_path = get_full_path(source_folder, new_file_name)
    
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
        source_path = get_full_path(source_folder, file)
        file_name, file_extension = os.path.splitext(file)
        new_file_name = f"{file_name}_processed{file_extension}"
        dest_path = get_full_path(archive_folder, new_file_name)
        
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

async def process_ftp_files(recon_type: str) -> bool:
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
    # Base path and folder configuration
    ftp_instance_folder = os.getenv('FTP_INSTANCE_FOLDER_NAME', 'dev').strip('/')
    primerecon_folder = os.getenv('PRIMERECON_FTP_FOLDER', 'primerecon').strip('/')
    primerecon_archive_folder = os.getenv('PRIMERECON_ARCHIVE_FOLDER', 'primerecon_archive').strip('/')
    
    # Construct full paths
    full_primerecon_path = get_full_path(ftp_instance_folder, primerecon_folder)
    full_archive_path = get_full_path(ftp_instance_folder, primerecon_archive_folder)
    
    incoming_endpoint = os.getenv('PRIME_ADAPTER_RECON_INCOMING_ENDPOINT', 'http://localhost:8080/primeadapter/v3/api/recon/incoming')
    outgoing_endpoint = os.getenv('PRIME_ADAPTER_RECON_OUTGOING_ENDPOINT', 'http://localhost:8080/primeadapter/v3/api/recon/outgoing')

    # Log environment variables
    logger.info(f"Recon type: {recon_type.upper()}")
    logger.info(f"FTP server: {host}:{port}")
    logger.info(f"FTP user: {user}")
    logger.info(f"Full primerecon path: {full_primerecon_path}")
    logger.info(f"Full archive path: {full_archive_path}")
    logger.info(f"Incoming endpoint: {incoming_endpoint}")
    logger.info(f"Outgoing endpoint: {outgoing_endpoint}")

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
        logger.info(f"Connected to SFTP server: {host}:{port} as {user}")
        
        # Ensure folders exist
        ensure_folder_exists(sftp, full_primerecon_path)
        ensure_folder_exists(sftp, full_archive_path)

        # Get list of txt files (excluding error files)
        files = sftp.listdir(full_primerecon_path)
        txt_files = [f for f in files if f.endswith('.txt') and f.lower().find(recon_type.lower()) >= 0 and not f.endswith('_error.txt')]
        
        if not txt_files:
            logger.info("No files to process")
            return True

        recon_processor = ReconFileProcessor(incoming_endpoint, outgoing_endpoint)
        processed_files = []
        failed_files = []

        # Process files concurrently
        async def process_single_file(file: str):
            try:
                file_path = get_full_path(full_primerecon_path, file)
                content = await read_sftp_file(sftp, file_path)
                result = await recon_processor.process_file(file, content)
                
                if result.is_success:
                    processed_files.append(file)
                    logger.info(f"Successfully processed {file}: {result.message}")
                else:
                    failed_files.append(file)
                    logger.error(f"Failed to process {file}: {result.message}")
                    logger.error("Errors: " + "\n".join(result.errors))
                    handle_failed_file(sftp, full_primerecon_path, file)
                    
            except Exception as e:
                logger.error(f"Failed to process file {file}: {str(e)}")
                failed_files.append(file)
                handle_failed_file(sftp, full_primerecon_path, file)

        # Process all files concurrently
        await asyncio.gather(*[process_single_file(file) for file in txt_files])

        # Archive successfully processed files
        if processed_files:
            archive_processed_files(sftp, full_primerecon_path, full_archive_path, processed_files)
            logger.info(f"Successfully processed and archived {len(processed_files)} files")

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
    logger.info("Starting FTP file processing") 
    recon_type = os.getenv('RECON_TYPE')

    if not recon_type:
        logger.error("RECON_TYPE environment variable is not set.")
        sys.exit(1)
    elif recon_type.upper() not in ['INCOMING', 'OUTGOING']:
        logger.error(f"Invalid recon type: {recon_type}. Must be either INCOMING or OUTGOING.")
        sys.exit(1)
        
    success =  await process_ftp_files(recon_type)
    sys.exit(0 if success else 1)

if __name__ == "__main__":
    asyncio.run(main())