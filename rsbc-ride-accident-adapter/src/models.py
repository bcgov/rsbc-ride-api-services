from dataclasses import dataclass



@dataclass
class Accident:
    ACC_NO: str =''
    STANDARD_CITY_NAME: str =''
    BLOCK_NUMBER: str =''
    STREET_TRAVELLED_ON: str =''
    ST_TRAVELLED_ON_TYPE: str =''
    ST_TRAVELLED_ON_DIRECTION: str =''
    CROSS_STREET: str=''
    CROSS_STREET_TYPE: str =''


@dataclass
class GoogleGeoLocationRequest:
    address: str
    city: str
    province: str
    Country: str

@dataclass
class GoogleGeoLocationResponse:
     latitude: float=0.0
     longitude: float=0.0
     address : str=''
     city: str=''
     province: str=''
     country: str=''


@dataclass
class ProducerAPIGeolocationSubmittedResponse:
     status: int
     event_id: int
   
@dataclass
class LocationRequestPayload:
    latitude : str
    longitude: str
    requestedAddress: str
    fullAddress: str
  
@dataclass
class ProducerAPIGeolocationSubmittedRequest:
     accidentNumber: str
     locationRequestPayload: LocationRequestPayload
    

@dataclass
class HTTPHeaders:
    name: str
    value: str
    
   