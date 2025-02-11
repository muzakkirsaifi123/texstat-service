# TextStat Service

This service is part of the demo example on microservices patterns. This service capture text complexity metrics. It does not provide a RESTful API, but on the contrary ir provides RPC endpoints to do data processing. This processing consists on extract metircs from texts. Currently, basic matrics are implemented:

* Char count
* D-Estimate (or VOCD) diversity measurement.
* Polarity of the discourse

## Tech Stack

To implement this service, Spring Boot was used and `Maven` for dependency management. On the other hand, pluggins for `grpc` code generation where added at compile step. To start the service, you just run:

`mvn clean spring-boot:run`

To cleanly add `grpc` into the Spring Boot application [GRPC Spring Boot Starter](https://yidongnan.github.io/grpc-spring-boot-starter/) was used, as it provides flexibility to manage GRPC dependencies and add Contexts to the Spring App. In particular, with these facilities, a middleware was developed to validate authentication tokens using [go-auth-microservice](https://github.com/dpalmasan/go-auth-microservice).
