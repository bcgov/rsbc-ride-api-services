import csv
import requests

# Define the API endpoint
API_ENDPOINT = "http://localhost:8000/coordinates"
API_KEY = "a1b628c2-3d1c-48f6-8819-5615bbdcbfe8"

local_cache = []

def get_cached_location(address: str, city: str) -> dict:
  for location in local_cache:
    if location['request_address'] == address and location['city'] == city:
      print(f"Address: {address}, city: {city} already in cache")
      return location
  return None

# Function to call the API
def call_api(address: str, city: str) -> dict: 
  response = requests.get(
    API_ENDPOINT, 
    params={'address': address, 'city': city},
    headers={'api-key': API_KEY})
  if response.status_code == 200:
    location = response.json()
    
    if location['province'] != 'BC':
      print(f"Address: {address}, city: {city} is not in BC")
      return {'latitude': None, 'longitude': None}
    
    if (len(str(location['latitude'])) > 10):
      location['latitude'] = f"{location['latitude']:.6f}"
    if (len(str(location['longitude'])) > 10):
      location['longitude'] = f"{location['longitude']:.6f}"
    return {'latitude': location['latitude'], 'longitude': location['longitude'], 'address': location['address']}
  else:
    print(f"Address: {address}, city: {city} - Error: {response.status_code} - {response.text}")
    return {'latitude': None, 'longitude': None, 'address': None}

# Read the CSV file
csv_file_path = '/Users/adimarborges/dev/address_fix.csv'
csv_output_file_path = '/Users/adimarborges/dev/address_output_df_fix.csv'
with open(csv_file_path, mode='r') as file:
  with open(csv_output_file_path, mode='w') as output_file:
    csv_reader = csv.reader(file)
    csv_writer = csv.writer(output_file)
    csv_writer.writerow(['business_program', 'business_type', 'business_id', 'long', 'lat', 'precision', 'requested_address', 'submitted_address', 'full_address', 'databc_long', 'databc_lat', 'databc_score', 'databc_precision', 'alternate_source'])
    for row in csv_reader:
      id = row[0]
      address = row[1]
      city = row[2]
      form_type = row[3]
      if (city == 'UBC (VANCOUVER)'):
        city = 'Vancouver'

      result = get_cached_location(address, city)
      if result is None:
        result = call_api(address, city)
        local_cache.append({'request_address': address, 'city': city, 'latitude': result['latitude'], 'longitude': result['longitude'], 'address': result['address']})

      print(f"Address: {address}, latitude: {result['latitude']}, longitude: {result['longitude']}")
      csv_writer.writerow(['DF', form_type, id, result['longitude'], result['latitude'], None, f"{address}, {city}", f"{address}, {city}", result['address'], 'NA', 'NA', 0, None, 'google'])
  
  # |business_program|business_type|business_id|long|lat|precision|requested_address|submitted_address|full_address|databc_long|databc_lat|databc_score|databc_precision|alternate_source