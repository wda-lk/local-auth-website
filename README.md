# local-auth-website

## Modules
* backend - for the APIs and DB connection
* frontend
* data-analyzer

## Build
### Requirements
1. Mysql
2. Maven
3. Java8

* Open the root dir - `local-auth-website`
* Execute `mvn clean install`
* Execute `java -Dserver.port=8080 -jar backend/target/backend.jar`