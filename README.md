# name-svc
load name data and emit name events


## cicd options
- [github action](https://cloud.google.com/community/tutorials/cicd-cloud-run-github-actions)

## local options
- [test emulator](https://cloud.google.com/pubsub/docs/emulator)
- [pub/sub config](https://docs.spring.io/spring-cloud-gcp/docs/current/reference/html/#spring-cloud-gcp-pubsub-api-configuration)

## input options
- MultiPartFile upload
- Cloud Storage event

## output
- insert/update name records in `db`
- publish `name` event to topic