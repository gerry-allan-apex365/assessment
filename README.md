# Users Microservice
This repository is part of the Java developer technical assessment.

To run the project: mvn spring-boot:run
The application runs on port 8081

The project is a Spring Boot application that uses JPA to connect to an in memory H2 database and exposes CRUD operarations. 
The swagger documentation that discribes the endpoints is at: http://localhost:8081/swagger-ui/

The assessment excercise also comes with a Zuul API gateway for re-routing traffic to this microservice. The gateway is found at [Gateway](https://github.com/gerry-allan-apex365/gateway)
