
import pymssql
from src.utils import bi_prepquerystring,bi_prepinsertstring,bi_prepupsertstring

class DatabaseClass():
    def __init__(self,dbserver,dbuser,dbpass):
        self.dbserver = dbserver
        self.dbuser = dbuser
        self.dbpass = dbpass


class BiDBClass(DatabaseClass):

    def __init__(self,dbname,dbserver,dbuser,dbpass,logging):
        super().__init__(dbserver,dbuser,dbpass)
        self.dbserver = dbserver
        self.dbuser = dbuser
        self.dbpass = dbpass
        self.dbname=dbname
        self.logging = logging
        self.conn,self.cursor = self.getconnection()


    def getconnection(self):
        conn = pymssql.connect(server=self.dbserver, user=self.dbuser, password=self.dbpass, database=self.dbname)
        cursor = conn.cursor()
        self.logging.info("connected to db")
        return conn,cursor
    
    def querydata(self,schema,tablename,payload,primarykeys=None):
        # query data from table
        recordfound=False
        if primarykeys==None:
            qrystr=bi_prepquerystring(payload)
            query = f"SELECT * FROM {schema}.{tablename} WHERE {qrystr}"
            result = self.cursor.execute(query)
            rows = self.cursor.fetchall()
            self.logging.debug(rows)
            if len(rows)>0:
                recordfound=True
        elif primarykeys[0]=='NA':
            rows=[]
        else:
            qrystr=bi_prepquerystring(payload,primarykeys)
            query = f"SELECT * FROM {schema}.{tablename} WHERE {qrystr}"
            result = self.cursor.execute(query)
            rows = self.cursor.fetchall()
            self.logging.debug(rows)
            if len(rows)>0:
                recordfound=True

        return recordfound,rows

    def insertrow(self,schema,tablename,payload):
        # sql insert query
        insertstr=bi_prepinsertstring(payload,tablename,schema)
        self.logging.debug(insertstr)
        self.cursor.execute(insertstr)
        self.conn.commit()
        return True

    def upsertdata(self,schema,tablename,payload,primarykeys):
        upsertstr=bi_prepupsertstring(payload,tablename,schema,primarykeys)
        self.logging.debug(upsertstr)
        self.cursor.execute(upsertstr)
        self.conn.commit()
        return True
        

        


    # def upsertdata(self,schema,tablename,payload):
    #     # query string to insert or update
    #     query = f"MERGE {schema}.{tablename} AS target USING (SELECT * FROM OPENJSON('{payload}')) AS source ON target.id = source.id WHEN MATCHED THEN UPDATE SET target.id = source.id WHEN NOT MATCHED THEN INSERT (id) VALUES (source.id);"
    #     pass


# INSERT INTO RSBCODW.dfcms.case_license_conditions
# (drivers_license_no, case_seq_no, condition_cd, condition_dsc)
# VALUES('', 0, '', '');
