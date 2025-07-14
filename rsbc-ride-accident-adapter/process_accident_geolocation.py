#!/usr/bin/env python3
#!/usr/bin/env python3

import os
import asyncio
import logging
import sys
from dotenv import load_dotenv

from src.models import (
    Accident,
    GoogleGeoLocationRequest,
    HTTPHeaders,
    GoogleGeoLocationResponse,
    LocationRequestPayload,
    ProducerAPIGeolocationSubmittedRequest,
)
from src.database import Database
from src.accidentGeolocationController import AccidentGeolocationController, ProcessingStatus

# Load environment variables and configure logging
load_dotenv()
logger = logging.getLogger(__name__)
logging.basicConfig(level=logging.INFO)
address_cache = {}  # In-memory cache


def get_api_keys():
    return os.getenv('API_RIDE_KEY_GEOCODING'), os.getenv('API_RIDE_KEY_PRODUCER')


def get_accidents_from_db() -> list:
    db = Database()
    query = (
        "SELECT ACC_NO, STANDARD_CITY_NAME, BLOCK_NUMBER, STREET_TRAVELLED_ON, "
        "ST_TRAVELLED_ON_TYPE, ST_TRAVELLED_ON_DIRECTION, CROSS_STREET, CROSS_STREET_TYPE "
    )
    accidents = db.query_accidents(query)
    db.close()
    return accidents


def build_address(accident: Accident) -> str:
    if accident.BLOCK_NUMBER:
        return f"{accident.BLOCK_NUMBER} {accident.STREET_TRAVELLED_ON} {accident.ST_TRAVELLED_ON_TYPE}".strip()
    return f"{accident.STREET_TRAVELLED_ON} {accident.ST_TRAVELLED_ON_TYPE} / {accident.CROSS_STREET} {accident.CROSS_STREET_TYPE}".strip()


async def send_geolocation_request(controller, address, city, headers):
    endpoint = os.getenv("GEOLOCATION_URL")
    request = GoogleGeoLocationRequest(address, city, "BC", "CA")
    result = await controller.send_to_endpoint(endpoint, request, False, headers)
    log_send_result(endpoint, result)
    return result


async def send_producer_api_request(controller, acc_no, address, geolocation_response, headers):
    endpoint = os.getenv("PRODUCER_API_URL")
    payload = LocationRequestPayload(
        str(geolocation_response.latitude),
        str(geolocation_response.longitude),
        address,
        geolocation_response.address
    )
    request = ProducerAPIGeolocationSubmittedRequest(acc_no, payload)
    result = await controller.send_to_endpoint(endpoint, request, True, headers)
    log_send_result(endpoint, result)
    return result


def log_send_result(endpoint, result):
    logger.info(f"Result from {endpoint} - Status: {result.status}, Message: {result.message}, Errors: {result.errors}")
    logger.info(f"Data: {result.data}")




async def process_accident(accident: Accident, geo_key: str, producer_key: str, address_cache: dict)-> bool:
    logger.info(f"Processing accident number: {accident.ACC_NO}")
    controller = AccidentGeolocationController()
    headers_geo = HTTPHeaders("api-key", geo_key)
    headers_producer = HTTPHeaders("ride-api-key", producer_key)

    address = build_address(accident)

    # Check cache first
    if address in address_cache:
        logger.info(f"Using cached geolocation for address: {address}")
        geo_data = address_cache[address]
    else:
        # Not in cache — call API
        geo_result = await send_geolocation_request(controller, address, accident.STANDARD_CITY_NAME, headers_geo)
        if geo_result.status != ProcessingStatus.SUCCESS or not geo_result.data:
            return False  # Skip this accident if geolocation failed
        geo_data = GoogleGeoLocationResponse(**geo_result.data)
        address_cache[address] = geo_data  # Store in cache

    await send_producer_api_request(controller, accident.ACC_NO, address, geo_data, headers_producer)
    return True


async def main():
    geo_key, producer_key = get_api_keys()
    accidents = get_accidents_from_db()
    success = False

    if not accidents:
        logger.info("No accidents found.")
        sys.exit(0)
         
    

    for accident in accidents:
<<<<<<< Updated upstream
        accident_processed =await process_accident(accident, geo_key, producer_key, address_cache)
        if accident_processed and not success:
            sucess = True
    
=======
        if accident.STANDARD_CITY_NAME and not get_accidents_from_geolocation_db(db, accident.ACC_NO):
            await process_accident(accident, geo_key, producer_key, address_cache)
    
    db.close()
    success = True
>>>>>>> Stashed changes
    sys.exit(0 if success else 1)


if __name__ == "__main__":
    asyncio.run(main())
