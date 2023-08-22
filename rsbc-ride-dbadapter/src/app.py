
import fastapi
from fastapi.responses import PlainTextResponse,JSONResponse
import os
import logging
from src.utils import validatepayload
from src.dbfuncs import BiDBClass


router = fastapi.APIRouter()

numeric_level = getattr(logging, os.getenv('LOG_LEVEL').upper(), 10)
# Set up logging
logging.basicConfig(
    level=numeric_level,
    format='%(asctime)s %(levelname)s %(module)s:%(lineno)d [RIDE_DBADAPTER]: %(message)s'
)



@router.get('/ping')
async def pingmethod():
    return JSONResponse(status_code=200, content={"status":"working"})


@router.post('/senddata',response_class=JSONResponse)
async def senddata(payload: dict):
    respstatus = {"status": "failure"}
    status_code = 500
    try:
        payloadinput = payload.copy()
        if not validatepayload(payloadinput):
            status_code = 400
            raise Exception('Invalid payload')
        logging.info(payloadinput)
        if payloadinput['destination']=='bi':
            dbserver=os.getenv('BI_DB_SERVER')
            dbuser=os.getenv('BI_DB_USER')
            dbpassword=os.getenv('BI_DB_PASSWORD')
            dbname=os.getenv('BI_DB_NAME')
            schema=payloadinput['schema']
            tablename=payloadinput['tablename']
            datarows=payloadinput['data']
            bi_db_obj=BiDBClass(dbname,dbserver,dbuser,dbpassword)
            for rw in datarows:
                # bi_db_obj.upsertdata(schema,tablename,rw)
                recordfnd,rows=bi_db_obj.querydata(schema,tablename,rw)
                print(recordfnd)
                print(rows)
        status_code = 200
    except Exception as e:
        print(e)
        respstatus = {"status": "failure","error":str(e)}
    return JSONResponse(status_code=status_code, content=respstatus)