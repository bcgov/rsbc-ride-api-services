import io
from typing import List, Dict, Union, Tuple
import httpx
import asyncio
from datetime import datetime
import logging
import re
from enum import Enum

class ProcessingStatus(Enum):
    SUCCESS = "SUCCESS"
    FAILED = "FAILED"

class ProcessingResult:
    def __init__(self, status: ProcessingStatus, message: str = "", errors: List[str] = None):
        self.status = status
        self.message = message
        self.errors = errors or []
    
    @property
    def is_success(self) -> bool:
        return self.status == ProcessingStatus.SUCCESS

class ReconFileProcessor:
    def __init__(self, incoming_endpoint: str, outgoing_endpoint: str):
        self.incoming_endpoint = incoming_endpoint
        self.outgoing_endpoint = outgoing_endpoint
        self.logger = logging.getLogger(__name__)

    async def process_files(self, files: List[Dict[str, Union[str, io.BytesIO]]]) -> List[ProcessingResult]:
        """
        Process multiple files and return results for each
        """
        tasks = [self.process_file(file['name'], file['content']) for file in files]
        results = await asyncio.gather(*tasks, return_exceptions=True)
        
        processed_results = []
        for file, result in zip(files, results):
            if isinstance(result, Exception):
                processed_results.append(
                    ProcessingResult(
                        ProcessingStatus.FAILED,
                        f"Error processing file {file['name']}", 
                        [str(result)]
                    )
                )
            else:
                processed_results.append(result)
                
        return processed_results

    async def process_file(self, filename: str, content: io.BytesIO) -> ProcessingResult:
        """
        Process a single file and return its processing result
        """
        try:
            file_type = self.determine_file_type(filename)
            content_str = content.getvalue().decode('utf-8')
            
            # Parse records
            records = self.parse_csv(content_str, file_type)
            if not records:
                return ProcessingResult(
                    ProcessingStatus.FAILED,
                    f"No valid records found in file {filename}",
                    ["Empty or invalid file content"]
                )

            # Send to appropriate endpoint
            endpoint = self.incoming_endpoint if file_type == "incoming" else self.outgoing_endpoint
            send_result = await self.send_to_endpoint(endpoint, records, filename)
            
            if not send_result.is_success:
                return send_result

            self.logger.info(f"Successfully processed file: {filename}")
            return ProcessingResult(
                ProcessingStatus.SUCCESS,
                f"Successfully processed {len(records)} records from {filename}"
            )

        except UnicodeDecodeError as e:
            return ProcessingResult(
                ProcessingStatus.FAILED,
                f"File encoding error in {filename}",
                [str(e)]
            )
        except Exception as e:
            self.logger.error(f"Error processing file {filename}: {str(e)}")
            return ProcessingResult(
                ProcessingStatus.FAILED,
                f"Failed to process file {filename}",
                [str(e)]
            )

    def determine_file_type(self, filename: str) -> str:
        return "incoming" if "incoming" in filename.lower() else "outgoing"

    def parse_csv(self, content: str, file_type: str) -> List[Dict[str, Union[str, int]]]:
        """
        Parse CSV content and return records
        """
        lines = content.strip().split('\n')
        records = []
        errors = []
        
        for line_num, line in enumerate(lines, 1):
            try:
                if not line.strip():  # Skip empty lines
                    continue
                
                # Stop processing if we hit the delimiter
                if line.strip().startswith('---'):
                    break
                    
                record = self.parse_incoming_record(line) if file_type == "incoming" else self.parse_outgoing_record(line)
                
                if record:
                    records.append(record)
                else:
                    errors.append(f"Line {line_num}: Invalid record format")
                    
            except Exception as e:
                errors.append(f"Line {line_num}: {str(e)}")
        
        if errors:
            self.logger.warning(f"Encountered {len(errors)} parsing errors:\n" + "\n".join(errors))
            
        return records

    def parse_incoming_record(self, line: str) -> Dict[str, str]:
        """Parse incoming record with enhanced error checking"""
        try:
            pattern = r'(\w+\s+\d+\s+\d+:\d+),\s*(.*?),\s*(\w),\s*(\w+)'
            match = re.match(pattern, line)
            
            if not match:
                raise ValueError(f"Invalid format in incoming record: {line}")
            
            date_time_str, ticket_file_name, ticket_status, prime_server_name = match.groups()
            
            # Validate date format
            try:
                datetime.strptime(date_time_str, '%b %d %H:%M')
            except ValueError:
                raise ValueError(f"Invalid date format: {date_time_str}")
            
            return {
                "primeServerSentDTM": date_time_str,
                "ticketFileName": ticket_file_name.strip(),
                "ticketStatus": ticket_status.strip(),
                "primeServerName": prime_server_name.strip(),
                "reconFileName": f"PRIME_RSI_prod_daily_incoming_report_{datetime.now().strftime('%Y%m%d')}.txt"
            }
        except Exception as e:
            self.logger.error(f"Error parsing incoming record: {str(e)}")
            raise

    def parse_outgoing_record(self, line: str) -> Dict[str, Union[str, int]]:
        """Parse outgoing record with enhanced error checking"""
        try:
            parts = [part.strip() for part in line.split(',')]
            
            if len(parts) < 3:
                raise ValueError(f"Insufficient fields in record. Expected at least 3, got {len(parts)}")
            
            record = {
                "serverSentDtm": parts[0].strip(),
                "ticketNumber": parts[1].strip(),
                "serverCode": parts[2].strip(),
                "xValue": parts[3].strip() if len(parts) > 3 else "0",
                "yValue": parts[4].strip() if len(parts) > 4 else "0"
            }
            return record

        except Exception as e:
            self.logger.error(f"Error parsing outgoing record: {str(e)}")
            raise

    async def send_to_endpoint(self, endpoint: str, records: List[Dict[str, Union[str, int]]], filename: str) -> ProcessingResult:
        """
        Send records to endpoint with enhanced error handling and status reporting
        """
        try:
            timeout = httpx.Timeout(30.0, connect=10.0)  # 30 seconds total, 10 seconds connect timeout
            async with httpx.AsyncClient(timeout=timeout) as client:
                json_body = {
                    "records": records,
                    "filename": filename
                }
                
                response = await client.post(endpoint, json=json_body)
                
                if response.status_code == 200:
                    self.logger.info(f"Successfully sent and processed {len(records)} records to {endpoint}")
                    return ProcessingResult(
                        ProcessingStatus.SUCCESS,
                        f"Successfully sent {len(records)} records to endpoint"
                    )
                
                # Handle different error status codes
                error_message = f"HTTP {response.status_code}"
                try:
                    error_detail = response.json()
                    error_message = f"{error_message}: {error_detail.get('message', 'Unknown error')}"
                except Exception:
                    error_message = f"{error_message}: {response.text[:200]}"
                    
                self.logger.error(f"ERROR send_to_endpoint : {str(error_message)}")
                
                return ProcessingResult(
                    ProcessingStatus.FAILED,
                    f"Failed to send records to endpoint",
                    [error_message]
                )
                
        except httpx.TimeoutException as e:
            return ProcessingResult(
                ProcessingStatus.FAILED,
                "Request timed out",
                [str(e)]
            )
        except httpx.RequestError as e:
            return ProcessingResult(
                ProcessingStatus.FAILED,
                "Failed to connect to endpoint",
                [str(e)]
            )
        except Exception as e:
            return ProcessingResult(
                ProcessingStatus.FAILED,
                "Unexpected error while sending records",
                [str(e)]
            )