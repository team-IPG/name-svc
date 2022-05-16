# name-svc

![Coverage](.github/badges/jacoco.svg)

services responsible for loading name data emitting events for voice generation

## REST APIs

### Update Status

GET ```/employee/{id}/status/{true/false}```

Example: De-activate a user with id = `Jose-Aldo` (aka 'opt-out')

```/employee/Jose-Aldo/status/false```

Example: Activate a user with id = `Jose-Aldo`

```/employee/Jose-Aldo/status/false```

### Find Users (by id, firstname or lastname)

GET ```/findEmployee/{search-term}```

Example: Find all users with an id or firstname or lastname that begins with `jo`

```/findEmployee/jo```

Result:
```json
[
    {
        "firstName": "Jose",
        "lastName": "Aldo",
        "preferredName": "ho-say",
        "preferredPreset": "PRESET_2",
        "preferredSpeed": 1,
        "active": true
    },
    {
        "firstName": "Jose",
        "lastName": "Luis",
        "preferredName": "Joseph",
        "preferredPreset": "PRESET_1",
        "preferredSpeed": 1,
        "active": true
    }
]

```

### Get User Details

GET ```/employee/{id}```

Example: Get user details for user with id = `Jose-Aldo`

```/employee/Jose-Aldo```

Result:
```json
{
    "firstName": "Jose",
    "lastName": "Aldo",
    "preferredName": "ho-say",
    "preferredPreset": "PRESET_2",
    "preferredSpeed": 1,
    "active": true
}
```

### Update User Preferences

POST `/employeePreferences/{id}`

BODY:
```json
{
   "preferredName": <preferred_name_string>,
   "preferredPreset": <preset_name_string>,
   "preferredSpeed": <decimal>
}
```

Example: Update preferences for user with id = `Jose-Aldo`

Example BODY:
```json
{
   "preferredName": "jo-say",
   "preferredPreset": "PRESET_4",
   "preferredSpeed": 1.2
}
```


## Local Run: Pre-Requisites

Download [GCP credentials json file](https://github.com/team-IPG/foundation/blob/main/key.json) and store on your machine

```bash
# example
/Users/john/key.json
```
## Local Run: Gradle Wrapper & terminal

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