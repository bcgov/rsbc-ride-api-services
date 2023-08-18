#!/bin/bash

mvn clean package -Dmaven.test.skip=true

AGENT_FILE=opentelemetry-javaagent.jar
if [ ! -f "${AGENT_FILE}" ]; then
  curl -k -L https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar --output ${AGENT_FILE}
fi

# export OTEL_SERVICE_NAME=geocodersvc
# export OTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:5555

# export MIN_CONFIDENCE_SCORE=60
# export GOOGLE_FAIL_OVER_ENABLED=TRUE
# export DATA_BC_API_URL=http://localhost:8082
# export GOOGLE_API_KEY=AIzaSyDQVxyW6-V3r-1jc90P4bQ2ulm2uJxlq1I
# export GOOGLE_API_ROOT_URL=http://localhost:8082/maps/api/geocode/json

#export SERVER_PORT=8585

java -javaagent:./${AGENT_FILE} -jar target/geocodersvc-0.0.1-SNAPSHOT.jar
