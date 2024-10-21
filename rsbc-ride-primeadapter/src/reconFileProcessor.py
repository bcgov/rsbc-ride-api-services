import io
from typing import List, Dict, Union
import httpx
import asyncio
from datetime import datetime
import logging
import re

class ReconFileProcessor:
    def __init__(self, incoming_endpoint: str, outgoing_endpoint: str):
        self.incoming_endpoint = incoming_endpoint
        self.outgoing_endpoint = outgoing_endpoint
        self.logger = logging.getLogger(__name__)

    async def process_files(self, files: List[Dict[str, Union[str, io.BytesIO]]]):
        tasks = [self.process_file(file['name'], file['content']) for file in files]
        results = await asyncio.gather(*tasks, return_exceptions=True)
        processed_files = []
        for file, result in zip(files, results):
            if isinstance(result, Exception):
                self.logger.error(f"Error processing file {file['name']}: {result}")
            else:
                processed_files.append(file['name'])
        return processed_files

    async def process_file(self, filename: str, content: io.BytesIO):
        file_type = self.determine_file_type(filename)
        content_str = content.getvalue().decode('utf-8')
        
        records = self.parse_csv(content_str, file_type)
        
        if file_type == "incoming":
            await self.send_to_endpoint(self.incoming_endpoint, records, filename)
        else:
            await self.send_to_endpoint(self.outgoing_endpoint, records, filename)
        
        self.logger.info(f"Successfully processed file: {filename}")

    def determine_file_type(self, filename: str) -> str:
        return "incoming" if "incoming" in filename.lower() else "outgoing"

    def parse_csv(self, content: str, file_type: str) -> List[Dict[str, Union[str, int]]]:
        lines = content.strip().split('\n')
        records = []
        
        for line in lines:
            if file_type == "incoming":
                record = self.parse_incoming_record(line)
            else:
                record = self.parse_outgoing_record(line)
            
            if record:
                records.append(record)
        
        return records

    def parse_incoming_record(self, line: str) -> Dict[str, str]:
        try:
            # Use regular expression to split the line
            pattern = r'(\w+\s+\d+\s+\d+:\d+),\s*(.*?),\s*(\w),\s*(\w+)'
            match = re.match(pattern, line)
            
            if not match:
                raise ValueError(f"Invalid format in incoming record: {line}")
            
            date_time_str, ticket_file_name, ticket_status, prime_server_name = match.groups()
            
            return {
                "primeServerSentDTM": date_time_str,
                "ticketFileName": ticket_file_name.strip(),
                "ticketStatus": ticket_status.strip(),
                "primeServerName": prime_server_name.strip(),
                # TODO : Set the orginal file name
                "reconFileName": "PRIME_RSI_prod_daily_incoming_report_20241009.txt"
            }
        except Exception as e:
            self.logger.error(f"Error parsing incoming record: {str(e)}")
            return None

    def parse_outgoing_record(self, line: str) -> Dict[str, Union[str, int]]:
        try:
            parts = line.split(',')
            if len(parts) < 3:
                raise ValueError(f"Invalid number of parts in outgoing record: {len(parts)}")
            
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
            return None

    async def send_to_endpoint(self, endpoint: str, records: List[Dict[str, Union[str, int]]], filename: str):
        try:
            async with httpx.AsyncClient() as client:
                json_body = {
                    "records": records,
                    "filename": filename
                }
                response = await client.post(endpoint, json=json_body)
                response.raise_for_status()
                self.logger.info(f"Successfully sent {len(records)} records to {endpoint}")
        except httpx.HTTPStatusError as e:
            self.logger.error(f"HTTP error occurred while sending to {endpoint}: {e.response.status_code} {e.response.text}")
        except httpx.RequestError as e:
            self.logger.error(f"An error occurred while sending request to {endpoint}: {str(e)}")
        except Exception as e:
            self.logger.error(f"Unexpected error occurred while sending to {endpoint}: {str(e)}")