import io
from typing import List, Dict, Any
import httpx
import asyncio
import logging
import re
from enum import Enum
from dataclasses import dataclass, is_dataclass, asdict


logger = logging.getLogger(__name__)
logging.basicConfig(level= logging.INFO)



class ProcessingStatus(Enum):
    SUCCESS = "SUCCESS"
    FAILED = "FAILED"



class ProcessingResult:
    def __init__(self, status: ProcessingStatus, message: str = "", data :Dict[str, Any]= None, errors: List[str] = None):
        self.status = status
        self.message = message
        self.data = data or {}
        self.errors = errors or []
    
    @property
    def is_success(self) -> bool:
        return self.status == ProcessingStatus.SUCCESS

    

class AccidentGeolocationController:

    def __init__(self):
        pass

   
    async def send_to_endpoint(self, endpoint: str, params: Any , post: bool,  httpheaders: Any=None) -> ProcessingResult:
        """
        Send records to endpoint with enhanced error handling and status reporting
        """
        if (not is_dataclass(params) or not is_dataclass(httpheaders)):
            return ProcessingResult(
                ProcessingStatus.FAILED,
                "Bad Rrequest Format",
                None,
                [str(params)]
            )
            
        try:
            timeout = httpx.Timeout(30.0, connect=10.0)  # 30 seconds total, 10 seconds connect timeout
            async with httpx.AsyncClient(timeout=timeout) as client:
               
             
                if httpheaders:
                    headers ={
                        httpheaders.name: httpheaders.value
                    }
                
                else:
                    headers = {}

                if post:
                    response = await client.post(endpoint, json=asdict(params), headers= headers)
                else:
                    response = await client.get( endpoint, params=asdict(params), headers= headers)
                
                if response.status_code == 200:
                   
                    logger.info(f"Successfully sent and processed {endpoint}")
                    return ProcessingResult(
                        ProcessingStatus.SUCCESS,
                        f"Successfully sent request to endpoint",
                        response.json()
                    )
                
                # Handle different error status codes
                error_message = f"HTTP {response.status_code}"
                try:
                    error_detail = response.json()
                    error_message = f"{error_message}: {error_detail.get('message', 'Unknown error')}"
                except Exception:
                    error_message = f"{error_message}: {response.text[:200]}"
                    
                
                logger.info(f"ERROR send_to_endpoint : {str(error_message)}")
                
                return ProcessingResult(
                    ProcessingStatus.FAILED,
                    f"Failed to send data to endpoint",
                    None,
                    [error_message]
                )
                
        except httpx.TimeoutException as e:
            return ProcessingResult(
                ProcessingStatus.FAILED,
                "Request timed out",
                None,
                [str(e)]
            )
        except httpx.RequestError as e:
            return ProcessingResult(
                ProcessingStatus.FAILED,
                "Failed to connect to endpoint",
                None,
                [str(e)]
            )
        except Exception as e:
            return ProcessingResult(
                ProcessingStatus.FAILED,
                f"Unexpected error: {str(e)}",
                None,
                [str(e)]
            )