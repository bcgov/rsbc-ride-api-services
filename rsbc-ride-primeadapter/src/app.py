
import fastapi
from fastapi.responses import JSONResponse
import os
import io
import logging
from src.ftputil import FTPUtil
from src.reconFileProcessor import ReconFileProcessor


router = fastapi.APIRouter()

numeric_level = getattr(logging, os.getenv('LOG_LEVEL').upper(), 10)
# Set up logging
logging.basicConfig(
    level=numeric_level,
    format='%(asctime)s %(levelname)s %(module)s:%(lineno)d [RIDE_PRIMEADAPTER]: %(message)s'
)

def ensure_folder_exists(sftp, folder_path):
    try:
        sftp.stat(folder_path)
    except IOError:
        logging.info(f"Creating folder: {folder_path}")
        sftp.mkdir(folder_path)

def archive_processed_files(sftp, source_folder, archive_folder, files):
    archived_files = []
    for file in files:
        source_path = f'/{source_folder}/{file}'
        
        # Split the filename and extension
        file_name, file_extension = os.path.splitext(file)
        
        # Append "_processed" to the filename
        new_file_name = f"{file_name}_processed{file_extension}"
        
        dest_path = f'/{archive_folder}/{new_file_name}'
        
        try:
            # Check if the file exists in the destination
            try:
                sftp.stat(dest_path)
                # If we reach here, the file exists
                logging.info(f"File {new_file_name} already exists in archive folder. Removing it.")
                sftp.remove(dest_path)
            except IOError:
                # The file doesn't exist, which is fine
                pass

            sftp.rename(source_path, dest_path)
            logging.info(f"Moved and renamed {file} to {new_file_name} in archive folder")
            archived_files.append(new_file_name)
        except IOError as e:
            logging.error(f"Failed to move and rename {file} to archive folder: {str(e)}")
    return archived_files


@router.get('/ping')
async def pingmethod():
    return JSONResponse(status_code=200, content={"status":"working"})

@router.get('/ftp/read')
async def readFtpFiles():
    logging.debug("inside read ftp")
    host=os.getenv('PRIME_SFTP_SERVER') 
    port=os.getenv('PRIME_SFTP_SERVER_PORT', 22)
    user=os.getenv('PRIME_SFTP_USER') 
    user_password=os.getenv('PRIME_SFTP_PASS')    
    priv_key_str = os.getenv('PRIME_SFTP_PRIV_KEY_FILE')
    priv_key_file_passphrase = os.getenv('PRIME_SFTP_PRIV_KEY_FILE_PASSPHRASE', '')
    pub_key_str = os.getenv('PRIME_SFTP_PUB_KEY_FILE')   
    primerecon_folder = os.getenv('PRIMERECON_FTP_FOLDER', 'primerecon')
    primerecon_archive_folder = os.getenv('PRIMERECON_ARCHIVE_FOLDER', 'primerecon_archive')
    incoming_endpoint = os.getenv('PRIME_ADAPTER_RECON_INCOMING_ENDPOINT', 'http://localhost:8080/primeadapter/v3/api/recon/incoming')
    outgoing_endpoint = os.getenv('PRIME_ADAPTER_RECON_OUTGOING_ENDPOINT', 'http://localhost:8080/primeadapter/v3/api/recon/outgoing') 
       
    try:
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

        # Ensure both folders exist
        ensure_folder_exists(sftp, f'/{primerecon_folder}')
        ensure_folder_exists(sftp, f'/{primerecon_archive_folder}')

        files = sftp.listdir(f'/{primerecon_folder}')
        txt_files = [f for f in files if f.endswith('.txt')]
        
        recon_processor = ReconFileProcessor(incoming_endpoint, outgoing_endpoint)
        processed_files = []

        for file in txt_files:
            file_path = f'/{primerecon_folder}/{file}'
            with sftp.open(file_path, 'r') as f:
                content = io.BytesIO(f.read())
                processed_files.append({'name': file, 'content': content})

        await recon_processor.process_files(processed_files)

        # Archive processed files
        archived_files = archive_processed_files(sftp, primerecon_folder, primerecon_archive_folder, txt_files)

        ftputil.release_sftp_channel()
        return JSONResponse(status_code=200, content={
            "status": "Files processed and archived successfully",
            "processed_files": len(processed_files),
            #"archived_files": len(archived_files)
        })
    except Exception as e:
        logging.error(f"Failed to process SFTP files: {e}")
        return JSONResponse(status_code=500, content={"status": "error", "message": str(e)})




