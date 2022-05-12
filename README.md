# name-svc

![Coverage](.github/badges/jacoco.svg)

service responsible for loading name data emitting events for voice generation

## pre-requisite

Download [GCP credentials json file](https://github.com/team-IPG/foundation/blob/main/key.json) and store on your machine

```bash
# example
/Users/john/key.json
```
## run name-svc from terminal

Execute the gradle wrapper bootRun task, passing location to gcp json file
```bash
./gradlew bootRun --args="--spring.cloud.gcp.credentials.location=file:/Users/john/key.json"
```

The bootRun task will build the project using Gradle and start the Spring boot application on port 8080 (or whatever is specified in application.properties for server.port)

## run name-svc from IDE of choice

Using gradle features of your IDE, 
1. Pull up the `run configuration settings` for the `bootRun` Gradle task
2. Set the following environment variable
   `GOOGLE_APPLICATION_CREDENTIALS=/Users/john/key.json`
3. Run the `bootRun` task

The `bootRun` task will build the project using Gradle wrapper and start the Spring boot application on port 8080 (or whatever is specified in `application.yml` for `server.port`)



## cicd options
- [github action](https://cloud.google.com/community/tutorials/cicd-cloud-run-github-actions)

## local options
- [gcp pub/sub test emulator](https://cloud.google.com/pubsub/docs/emulator)

## tech
- [yugabyte support](https://github.com/yugabyte/spring-data-yugabytedb)
- [gcp pub/sub](https://docs.spring.io/spring-cloud-gcp/docs/current/reference/html/#spring-cloud-gcp-pubsub-api-configuration)


## input options
- MultiPartFile upload
- Cloud Storage event

## output
- insert/update name records in `yugabyte db`
- publish `name-update` event to gcp pub/sub `name-update-topic`