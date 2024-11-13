from typing import Annotated, Union
from fastapi import FastAPI, Header, status
import logging
import os

from fastapi.responses import JSONResponse
from pydantic import BaseModel
from .googlemaps_geocoding_adapter import GoogleMapsGeocodingAdapter
from .error_handler_middleware import ErrorHandlerMiddleware
from .location import Location

class Message(BaseModel):
    message: str

numeric_level = getattr(logging, os.getenv('LOG_LEVEL', default='INFO').upper(), 10)
logging.basicConfig(
    level=numeric_level,
    format='%(levelname)s [RIDE_GEOCODING_SVC] %(module)s:%(lineno)d %(message)s'
)

if os.getenv('GOOGLE_MAPS_API_KEY') is None:
    logging.error("GOOGLE_MAPS_API_KEY environment variable not set.")
    exit(1)

if os.getenv('ALLOWED_API_KEYS') is None:
    logging.error("ALLOWED_API_KEYS environment variable not set.")
    exit(1)

app = FastAPI()
app.add_middleware(ErrorHandlerMiddleware)
geocoding_adapter = GoogleMapsGeocodingAdapter(api_key=os.getenv('GOOGLE_MAPS_API_KEY'))
allowed_api_keys = os.getenv('ALLOWED_API_KEYS', '').split(',')

@app.get("/")
def read_root():
    logging.debug("/ endpoint triggered")
    return {"message": "Geocoding Service V-1.0 is up and running!"}

@app.get("/ping")
def ping():
    logging.debug("/ping endpoint triggered")
    return {"status": "working"}

@app.get("/coordinates", responses={404: {"model": Message}, 401: {"model": Message}})
def coordinates(
    address: str,
    city: str,
    province: str = 'BC',
    country: str = 'CA',
    api_key: Annotated[Union[str, None], Header()] = None) -> Location:
    logging.debug(f"/coordinates endpoint triggered with address: {address}, city: {city}, province: {province}, country: {country}")

    if api_key not in allowed_api_keys:
        logging.warning(f"Unauthorized request with API key {api_key}")
        return JSONResponse(status_code=status.HTTP_401_UNAUTHORIZED, content={"message": "Unauthorized"})

    logging.info(f"API key {api_key[0:5]}*** authorized")
    result = geocoding_adapter.get_location(address=address, city=city, province=province, country=country)
    if result.location is None:
        return JSONResponse(status_code=status.HTTP_404_NOT_FOUND, content={"message": result.error})

    return result.location