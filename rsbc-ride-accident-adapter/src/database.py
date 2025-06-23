import os
import logging
import pymssql

from typing import List, Optional
from dotenv import load_dotenv
from src.models import Accident

logger = logging.getLogger(__name__)
logging.basicConfig(level=logging.INFO)
load_dotenv()

class Database:

    def __init__(self):
        self.connection = self._connect()

    def _connect(self) -> Optional[pymssql.Connection]:
        """Connect to TAS SQL Server database. Returns connection if successful, None if failed."""
        try:
            username = os.getenv('RSBCODW_DB_USERNAME')
            password = os.getenv('RSBCODW_DB_PASSWORD')
            host = os.getenv('TAS_DB_HOST')
            port = int(os.getenv('TAS_DB_PORT', '1433'))
            database = os.getenv('TAS_DB_NAME')

            connection = pymssql.connect(
                server=host,
                user=username,
                password=password,
                database=database,
                port=port,
                charset='UTF-8'
            )
            logger.info("Connected to SQL Server database using pymssql.")
            return connection

        except Exception as e:
            logger.error(f"Failed to connect to database: {str(e)}")
            return None

    def close(self):
        if self.connection:
            self.connection.close()
            logger.info("Database connection closed.")

    


    def query_accidents(self, query :str )-> List [Accident]:
        """run query """
        sql = query+ " FROM TAS.ACCIDENT "
        try:
                cursor = self.connection.cursor() 
                cursor.execute (sql, ())
                accidents =[ Accident(*row )for row in cursor.fetchall()]
                return accidents
            
        except Exception as e:
                logger.error(f"Failed query: {str(e)}")
                return None
