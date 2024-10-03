
import fastapi
from fastapi.responses import PlainTextResponse,JSONResponse
import os
import logging
from src.ftputil import FTPUtil


router = fastapi.APIRouter()

numeric_level = getattr(logging, os.getenv('LOG_LEVEL').upper(), 10)
# Set up logging
logging.basicConfig(
    level=numeric_level,
    format='%(asctime)s %(levelname)s %(module)s:%(lineno)d [RIDE_PRIMEADAPTER]: %(message)s'
)



@router.get('/ping')
async def pingmethod():
    return JSONResponse(status_code=200, content={"status":"working"})


@router.get('/ftp/read')
async def readFtpFiles():
    logging.debug("inside read ftp")
    host=os.getenv('PRIME_SFTP_SERVER') 
    port=22
    user=os.getenv('PRIME_SFTP_USER') 
    user_password=os.getenv('PRIME_SFTP_PASS')    
    priv_key_file = os.getenv('PRIME_SFTP_PRIV_KEY_FILE')
    priv_key_file_passphrase = os.getenv('PRIME_SFTP_PRIV_KEY_FILE_PASSPHRASE')
    pub_key_file = os.getenv('PRIME_SFTP_PUB_KEY_FILE')    
       
    try:
        # Initialize the SFTP helper with necessary arguments
        ftputil = FTPUtil(
            host,
            port,
            user,
            user_password,
            priv_key_file,
            pub_key_file,
            passphrase=priv_key_file_passphrase,
            known_hosts=None
        )
        sftp = ftputil.acquire_sftp_channel()
        files = sftp.listdir('/')
        logging.debug(f"Files in SFTP root: {files}")
        sftp.release_sftp_channel()
        return JSONResponse(status_code=200, content={"status":"connected to ftp"})
    except Exception as e:
            logging.error(f"Failed to connect to SFTP: {e}")
            return {"status": "error", "message": str(e)}




