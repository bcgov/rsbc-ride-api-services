from fastapi import FastAPI
import logging
import os

numeric_level = getattr(logging, os.getenv('LOG_LEVEL',default='INFO').upper(), 10)
logging.basicConfig(
    level=numeric_level,
    format='%(asctime)s %(levelname)s [RIDE_GEOCODING_SVC] %(module)s:%(lineno)d %(message)s'
)

app = FastAPI()

@app.get("/")
def read_root():
    logging.debug("/ endpoint triggered")
    return {"message": "Geocoding Service is up and running!"}

@app.get("/ping")
def ping():
    logging.debug("/ping endpoint triggered")
    return {"status": "working"}
