import pytest
from unittest.mock import Mock
from src.googlemaps_geocoding_adapter import GoogleMapsGeocodingAdapter
from src.location import Location
from src.get_location_result import LocationResult

@pytest.fixture
def mock_client(mocker):
  mock_client = mocker.patch('googlemaps.Client').return_value
  return mock_client

@pytest.fixture
def adapter(mock_client):
  api_key = 'fake_api_key'
  return GoogleMapsGeocodingAdapter(api_key)

def test_init(adapter, mock_client):
  assert adapter.client == mock_client

def test_get_address_component_part(adapter):
  address_components = [
    {'types': ['locality'], 'short_name': 'Vancouver', 'long_name': 'Vancouver'},
    {'types': ['country'], 'short_name': 'CA', 'long_name': 'Canada'}
  ]
  assert adapter._get_address_component_part(address_components, 'locality') == 'Vancouver'
  assert adapter._get_address_component_part(address_components, 'country') == 'CA'
  assert adapter._get_address_component_part(address_components, 'administrative_area_level_1') == ''

def test_get_country(adapter):
  address_components = [{'types': ['country'], 'short_name': 'CA', 'long_name': 'Canada'}]
  assert adapter._get_country(address_components) == 'CA'

def test_get_province(adapter):
  address_components = [{'types': ['administrative_area_level_1'], 'short_name': 'BC', 'long_name': 'British Columbia'}]
  assert adapter._get_province(address_components) == 'BC'

def test_get_city(adapter):
  address_components = [{'types': ['locality'], 'short_name': 'Vancouver', 'long_name': 'Vancouver'}]
  assert adapter._get_city(address_components) == 'Vancouver'

def test_get_location_success(adapter, mock_client):
  mock_client.geocode.return_value = [{
    'geometry': {'location': {'lat': 49.2827, 'lng': -123.1207}},
    'formatted_address': 'Vancouver, BC, Canada',
    'address_components': [
      {'types': ['locality'], 'short_name': 'Vancouver', 'long_name': 'Vancouver'},
      {'types': ['administrative_area_level_1'], 'short_name': 'BC', 'long_name': 'British Columbia'},
      {'types': ['country'], 'short_name': 'CA', 'long_name': 'Canada'}
    ]
  }]
  result = adapter.get_location('123 Main St', 'Vancouver')
  assert isinstance(result, LocationResult)
  assert isinstance(result.location, Location)
  assert result.error is None
  assert result.location.city == 'Vancouver'
  assert result.location.province == 'BC'
  assert result.location.country == 'CA'
  assert result.location.latitude == 49.2827
  assert result.location.longitude == -123.1207

def test_get_location_no_results(adapter, mock_client):
  mock_client.geocode.return_value = []
  result = adapter.get_location('123 Main St', 'Vancouver')
  assert isinstance(result, LocationResult)
  assert result.location is None
  assert result.error is not None
  assert 'No geocode results found' in result.error

def test_get_location_partial_match(adapter, mock_client):
  mock_client.geocode.return_value = [{'partial_match': True}]
  result = adapter.get_location('123 Main St', 'Vancouver')
  assert isinstance(result, LocationResult)
  assert result.location is None
  assert result.error is not None
  assert 'Partial match found' in result.error

def test_get_location_invalid_address(adapter, mock_client):
  mock_client.geocode.return_value = None
  result = adapter.get_location('Invalid Address', 'Nowhere')
  assert isinstance(result, LocationResult)
  assert result.location is None
  assert result.error is not None
  assert 'No geocode results found' in result.error

def test_get_location_api_error(adapter, mock_client):
  with pytest.raises(Exception):
    mock_client.geocode.side_effect = Exception('API Error')
    adapter.get_location('123 Main St', 'Vancouver')
  