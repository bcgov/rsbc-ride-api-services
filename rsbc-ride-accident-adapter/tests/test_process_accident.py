import pytest
from unittest.mock import AsyncMock, patch, MagicMock
from src.models import Accident, GoogleGeoLocationResponse
from process_accident_geolocation import  process_accident
from src.accidentGeolocationController import ProcessingStatus





@pytest.fixture
def test_accident():
    return Accident(
        ACC_NO=123,
        STANDARD_CITY_NAME="Vancouver",
        BLOCK_NUMBER="123",
        STREET_TRAVELLED_ON="Main St",
        ST_TRAVELLED_ON_TYPE="Ave",
        ST_TRAVELLED_ON_DIRECTION=None,
        CROSS_STREET=None,
        CROSS_STREET_TYPE=None
    )


@pytest.fixture
def test_geolocation_response():
    return GoogleGeoLocationResponse(
        latitude="49.2827",
        longitude="-123.1207",
        address="123 Main St",
        city="Vancouver",
        province="BC",
        country="CA",
    )


@pytest.mark.asyncio
async def test_process_accident_calls_geolocation_and_producer_api(test_accident, test_geolocation_response):
    address_cache = {}

    controller_mock = AsyncMock()
    controller_mock.send_to_endpoint.side_effect = [
        MagicMock(status=ProcessingStatus.SUCCESS, data=test_geolocation_response.__dict__),
        MagicMock(status=ProcessingStatus.SUCCESS, data={"result": "ok"})
    ]

    with patch("process_accident_geolocation.AccidentGeolocationController", return_value=controller_mock), \
         patch("process_accident_geolocation.HTTPHeaders"):
        await process_accident(test_accident, "test-geo-key", "test-producer-key", address_cache)

        assert controller_mock.send_to_endpoint.call_count == 2
        assert len(address_cache) == 1
        assert "123 Main St Ave" in address_cache


@pytest.mark.asyncio
async def test_process_accident_uses_cache(test_accident, test_geolocation_response):
    address = "123 Main St Ave"
    address_cache = {
        address: test_geolocation_response
    }

    controller_mock = AsyncMock()
    controller_mock.send_to_endpoint.side_effect = [
        MagicMock(status=ProcessingStatus.SUCCESS, data={"result": "ok"})
    ]

    with patch("process_accident_geolocation.AccidentGeolocationController", return_value=controller_mock), \
         patch("process_accident_geolocation.HTTPHeaders"):
        await process_accident(test_accident, "test-geo-key", "test-producer-key", address_cache)

        # Should skip first API call and only call producer API
        assert controller_mock.send_to_endpoint.call_count == 1


@pytest.mark.asyncio
async def test_process_accident_on_failed_geolocation(test_accident):
    address_cache = {}

    controller_mock = AsyncMock()
    controller_mock.send_to_endpoint.return_value = MagicMock(
        status=ProcessingStatus.FAILED, data=None
    )

    with patch("process_accident_geolocation.AccidentGeolocationController", return_value=controller_mock), \
         patch("process_accident_geolocation.HTTPHeaders"):
        await process_accident(test_accident, "test-geo-key", "test-producer-key", address_cache)

        
        assert controller_mock.send_to_endpoint.call_count == 2
        assert len(address_cache) == 1
        assert "123 Main St Ave" in address_cache
