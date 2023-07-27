
.PHONY: build_start down local_build run_tests metrics_stack_up

# Docker build and start the local docker stack
build_start:
	docker-compose -f docker-compose.yml build --no-cache && docker-compose -f docker-compose.yml up -d --force-recreate $(c)

# Stop the local docker stack
down:
	docker-compose -f docker-compose.yml down $(c)

# Perform local maven build
local_build:
	mvn clean install -DskipTests
# Perform local unit tests
run_tests:
	mvn clean compile test

# Spin up the local metrics stack
metrics_stack_up:
	cd local_dev/local_metrics && docker-compose -f docker-compose.yml up -d --force-recreate $(c)

# Spin down the local metrics stack
metrics_stack_down:
	cd local_dev/local_metrics && docker-compose -f docker-compose.yml down $(c)
