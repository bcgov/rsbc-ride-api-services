import logging
from datetime import datetime
import os
import socket

# Configure logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger(__name__)

def main():
    try:
        # Get environment information
        hostname = socket.gethostname()
        current_time = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        
        # Print and log Hello World
        message = f"Hello World from {hostname} at {current_time}"
        print(message)
        logger.info(message)
        
        # Log environment variables (helpful for debugging)
        logger.info("Environment Variables:")
        for key, value in os.environ.items():
            if key.startswith('OPENSHIFT_') or key.startswith('KUBERNETES_'):
                logger.info(f"{key}: {value}")

        logger.info(f"PRIME_ADAPTER_RECON_INCOMING_ENDPOINT: {os.getenv('PRIME_ADAPTER_RECON_INCOMING_ENDPOINT')}")
        logger.info(f"PRIME_ADAPTER_RECON_OUTGOING_ENDPOINT: {os.getenv('PRIME_ADAPTER_RECON_OUTGOING_ENDPOINT')}")
        logger.info(f"PRIMERECON_FTP_FOLDER: {os.getenv('PRIMERECON_FTP_FOLDER')}")
        logger.info(f"PRIMERECON_ARCHIVE_FOLDER: {os.getenv('PRIMERECON_ARCHIVE_FOLDER')}")
        
        # Simulate some work
        logger.info("Starting task processing...")
        # Add a small delay to simulate work
        import time
        time.sleep(2)
        logger.info("Task processing completed successfully")
        
    except Exception as e:
        logger.error(f"An error occurred: {str(e)}")
        raise

if __name__ == "__main__":
    main()