# FROM registry.access.redhat.com/ubi8/python-39
FROM artifacts.developer.gov.bc.ca/rbe5-images/python:3.9-slim-buster

# RUN addgroup --system app && adduser --system --group app

ENV PYTHONDONTWRITEBYTECODE 1
ENV PYTHONUNBUFFERED 1
ENV ENVIRONMENT prod
ENV TESTING 0


WORKDIR /app
COPY . .
# COPY requirements.txt .
# COPY main.py .
# RUN pip install --no-cache-dir -r requirements.txt
# RUN chown -R app:app /app
# USER app
# CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "5000"]
# USER 1001
RUN /usr/local/bin/python -m pip install --upgrade pip
RUN pip install --no-cache-dir -r requirements.txt
CMD ["gunicorn", "main:api", "-w", "2", "-k", "uvicorn.workers.UvicornWorker", "-b", "0.0.0.0:5000","--timeout","2000","--error-logfile", "-"]
