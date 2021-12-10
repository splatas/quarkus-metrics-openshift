# HELP
# This will output the help for each task
.PHONY: help

help: ## This help.
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)

.DEFAULT_GOAL := help


# Docker Tasks:

deploy-infra:
	docker-compose -f ./rhdg-meter/src/main/docker/docker-compose.yml up -d

undeploy-infra:
	docker-compose -f ./rhdg-meter/src/main/docker/docker-compose.yml down
	


# Dev tasks

effective-pom:
	mvn clean help:effective-pom > effective-pom.txt

dependency-tree:
	# mvn dependency:tree > dependency-tree.txt
	mvn quarkus:dependency-tree

boot:
	clear
	mvn -f benchmark-lib/pom.xml clean install -Dmaven.test.skip
	mvn -f rhdg-meter/pom.xml quarkus:dev -Dmaven.test.skip

run:
	clear
	mvn clean package -Dmaven.test.skip
	java -jar target/quarkus-app/quarkus-run.jar
	
deploy:
	

