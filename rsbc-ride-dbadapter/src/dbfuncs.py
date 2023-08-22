
import pymssql
from src.utils import bi_prepquerystring

class DatabaseClass():
    def __init__(self,dbserver,dbuser,dbpass):
        self.dbserver = dbserver
        self.dbuser = dbuser
        self.dbpass = dbpass


class BiDBClass(DatabaseClass):

    def __init__(self,dbname,dbserver,dbuser,dbpass):
        super().__init__(dbserver,dbuser,dbpass)
        self.dbserver = dbserver
        self.dbuser = dbuser
        self.dbpass = dbpass
        self.dbname=dbname
        self.conn,self.cursor = self.getconnection()

    def getconnection(self):
        conn = pymssql.connect(server=self.dbserver, user=self.dbuser, password=self.dbpass, database=self.dbname)
        cursor = conn.cursor()
        return conn,cursor
    
    def querydata(self,schema,tablename,payload):
        # query data from table
        recordfound=False
        qrystr=bi_prepquerystring(payload)
        query = f"SELECT * FROM {schema}.{tablename} WHERE {qrystr}"
        result = self.cursor.execute(query)
        rows = self.cursor.fetchall()
        print(rows)
        if len(rows)>0:
            recordfound=True

        return recordfound,rows


    # def upsertdata(self,schema,tablename,payload):
    #     # query string to insert or update
    #     query = f"MERGE {schema}.{tablename} AS target USING (SELECT * FROM OPENJSON('{payload}')) AS source ON target.id = source.id WHEN MATCHED THEN UPDATE SET target.id = source.id WHEN NOT MATCHED THEN INSERT (id) VALUES (source.id);"
    #     pass

