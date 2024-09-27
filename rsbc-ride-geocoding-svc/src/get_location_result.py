from dataclasses import dataclass
from typing import Optional
from .location import Location

@dataclass
class LocationResult:
  location: Optional[Location] = None
  error: str = None