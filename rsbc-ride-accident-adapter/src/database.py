import os
import logging
import pymssql

from typing import List, Optional,Tuple
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
            host = os.getenv('RSBCODW_DB_HOST')
            port = int(os.getenv('RSBCODW_DB_PORT', '1433'))
            database = ''

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


    def query_accident_from_geolocation(self, query :str,condition: str = "", params: Tuple = () )-> List [Accident]:
        """run query """
        sql = query+ " FROM GIS.GEOLOCATIONS "
        if condition:
            sql += f" WHERE {condition}"
        try:
                cursor = self.connection.cursor() 
                cursor.execute (sql, params)
                rows = cursor.fetchall()
                return rows
            
        except Exception as e:
                logger.error(f"Failed query: {str(e)}")
                return None

    

    def query_accidents_excluding_existing_geolocations(
        self,
        query :str,
        params: Tuple = ()
    ) -> List[Accident]:
        
    
        sql = query+ " FROM TAS.ACCIDENT a WHERE STANDARD_CITY_NAME IS NOT NULL AND  ACC_DATE  >=  DATEADD(YEAR, -13, GETDATE()) AND  NOT EXISTS ( SELECT 1 FROM GIS.GEOLOCATIONS g WHERE g.business_program = %s AND g.business_id = ACC_NO )"
    

        try:
            cursor = self.connection.cursor()
            cursor.execute(sql, params)
            accidents = [Accident(*row) for row in cursor.fetchall()]
            return accidents

        except Exception as e:
            logger.error(f"Failed query: {str(e)}")
            return None

