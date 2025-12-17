FROM artifacts.developer.gov.bc.ca/rbe5-images/python:3.9-slim-buster

ENV PYTHONDONTWRITEBYTECODE=1 PYTHONUNBUFFERED=1 ENVIRONMENT=prod TESTING=0
EXPOSE 5000

WORKDIR /app
COPY requirements.txt .
COPY /src/. .


RUN pip install --no-cache-dir --upgrade -r requirements.txt
CMD ["fastapi", "run", "main.py", "--port", "5000", "--workers", "1"]
