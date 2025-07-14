FROM artifacts.developer.gov.bc.ca/rbe5-images/python:3.9-slim-buster



WORKDIR /app
COPY . .

#RUN apt-get update
RUN /usr/local/bin/python -m pip install --upgrade pip
RUN pip install --no-cache-dir -r requirements.txt
# Command to run the script
CMD ["python", "process_accident_geolocation.py"]

#CMD ["pytest", "tests/test_process_accident.py"]