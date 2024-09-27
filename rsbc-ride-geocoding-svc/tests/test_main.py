import pytest
import os
import googlemaps

os.environ['GOOGLE_MAPS_API_KEY'] = 'test'
googlemaps.Client = lambda key: None

from pytest_mock import mocker
from fastapi.testclient import TestClient
from src.main import app
from src.location import Location
from src.get_location_result import LocationResult

@pytest.fixture
def client():
    return TestClient(app)

def test_read_root(client):
    response = client.get("/")
    assert response.status_code == 200
    assert response.json() == {"message": "Geocoding Service V-1.0 is up and running!"}

def test_ping(client):
    response = client.get("/ping")
    assert response.status_code == 200
    assert response.json() == {"status": "working"}

def test_coordinates_success_with_default_province_and_country(client, mocker):
    mock = mocker.patch('src.googlemaps_geocoding_adapter.GoogleMapsGeocodingAdapter.get_location', 
                        return_value=LocationResult(Location(
                            latitude=49.2827, 
                            longitude=-123.1207, 
                            address="1234 Main St, Vancouver, BC, CA", 
                            city="Vancouver", 
                            province="BC", 
                            country="CA")))
    response = client.get("/coordinates?address=1234+Main+St&city=Vancouver")
    assert response.status_code == 200
    assert response.json() == {"latitude": 49.2827, "longitude": -123.1207, "address": "1234 Main St, Vancouver, BC, CA", "city": "Vancouver", "province": "BC", "country": "CA"}
    assert mock.called_with(address="1234 Main St", city="Vancouver", province="BC", country="CA")

def test_coordinates_success_with_custom_province_and_country(client, mocker):
    mock = mocker.patch('src.googlemaps_geocoding_adapter.GoogleMapsGeocodingAdapter.get_location', 
                        return_value=LocationResult(Location(
                            latitude=49.2827, 
                            longitude=-123.1207, 
                            address="1234 Main St, Vancouver, CA, US", 
                            city="Vancouver", 
                            province="CA", 
                            country="US")))
    
    response = client.get("/coordinates?address=1234+Main+St&city=Vancouver&province=CA&country=US")
    assert response.status_code == 200
    assert response.json() == {"latitude": 49.2827, "longitude": -123.1207, "address": "1234 Main St, Vancouver, CA, US", "city": "Vancouver", "province": "CA", "country": "US"}
    assert mock.called_with(address="1234 Main St", city="Vancouver", province="CA", country="US")

def test_coordinates_failure_location_not_found(client, mocker):
    mock = mocker.patch('src.googlemaps_geocoding_adapter.GoogleMapsGeocodingAdapter.get_location', 
                        return_value=LocationResult(location=None, error="No geocode results found for address: 1234 Main St, Vancouver, BC, CA"))
    response = client.get("/coordinates?address=1234+Main+St&city=Vancouver")
    assert response.status_code == 404
    assert response.json() == {"message": "No geocode results found for address: 1234 Main St, Vancouver, BC, CA"}
    assert mock.called_with(address="1234 Main St", city="Vancouver", province="BC", country="CA")

def test_coordinates_failure_throw_exception(client, mocker):
    mock = mocker.patch('src.googlemaps_geocoding_adapter.GoogleMapsGeocodingAdapter.get_location', 
                        side_effect=Exception("Test exception"))
    response = client.get("/coordinates?address=1234+Main+St&city=Vancouver")
    assert response.status_code == 500
    assert response.json() == {"error": "Test exception"}
    assert mock.called_with(address="1234 Main St", city="Vancouver", province="BC", country="CA")