from dataclasses import dataclass


@dataclass
class Location:
    latitude: float
    longitude: float
    address: str
    city: str
    province: str
    country: str
