import logging
import googlemaps
from .location import Location
from .get_location_result import LocationResult

class GoogleMapsGeocodingAdapter:
  """
  Adapter class for interacting with the Google Maps Geocoding API.

  Attributes:
    client (googlemaps.Client): The Google Maps client initialized with the provided API key.

  Methods:
    __init__(api_key: str):
      Initializes the GoogleMapsGeocodingAdapter with the provided API key.

    _get_address_component_part(address_components: list, type: str, return_type: str = 'short_name') -> str:
      Retrieves a specific part of the address component based on the type and return type.

    _get_country(address_components: list) -> str:
      Retrieves the country from the address components.

    _get_province(address_components: list) -> str:
      Retrieves the province from the address components.

    _get_city(address_components: list) -> str:
      Retrieves the city from the address components.

    get_location(address: str, city: str, province: str = 'BC', country: str = 'CA') -> LocationResult:
      Geocodes the provided address and returns a LocationResult object containing the location details or an error message.
  """
  def __init__(self, api_key):
    self.client = googlemaps.Client(key=api_key)

  def _get_address_component_part(self, address_components: list, type: str, return_type: str = 'short_name') -> str:
    for address in address_components:
      if type in address['types']:
        return address[return_type]
    return ''

  def _get_country(self, address_components: list):
    return self._get_address_component_part(address_components, 'country')

  def _get_province(self, address_components: list):
    return self._get_address_component_part(address_components, 'administrative_area_level_1')
  
  def _get_city(self, address_components: list):
    return self._get_address_component_part(address_components, 'locality')

  def get_location(self, address: str, city: str, province: str = 'BC', country: str = 'CA') -> LocationResult:
    result = LocationResult(location=None, error=None)
    formatted_address = f'{address}, {city}, {province}, {country}'
    logging.info(f'Geocoding address: {formatted_address}')

    geocode_result = self.client.geocode(
    formatted_address,
    components={
      'administrative_area': province,
      'locality': city,
      'country': country
    })

    if not geocode_result or len(geocode_result) == 0:
      result.error = f'No geocode results found for address: {formatted_address}'
      logging.warning(result.error)
    elif 'partial_match' in geocode_result[0] and geocode_result[0]['partial_match']:
      result.error = f'Partial match found for address: {formatted_address}'
      logging.warning(result.error)
    else:
      result.location = Location(
        latitude=geocode_result[0]['geometry']['location']['lat'],
        longitude=geocode_result[0]['geometry']['location']['lng'],
        address=geocode_result[0]['formatted_address'],
        city=self._get_city(geocode_result[0]['address_components']),
        province=self._get_province(geocode_result[0]['address_components']),
        country=self._get_country(geocode_result[0]['address_components'])
      )
      logging.debug(f'Geocoded address: {formatted_address} to location: {result.location.__dict__}')

    return result