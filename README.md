## Framework/Library Used

1. Java JDK 11
2. Springboot 2.7.9
3. Gradle
4. Liquibase - to track, manage and apply database schema changes.
5. Swagger - to build, document, test and consume RESTful web services.
6. Mysql and Mongodb - for database.
7. Lombok - to minimize or remove the boilerplate code.
8. Actuator - to manage service health

# Docker Setup Guide

- To start the service
```
docker-compose -f docker-compose_eng.yaml up
```

- To stop the service
```
docker-compose -f docker-compose_eng.yaml down
```

# Local Setup Guide

This guide will show you how to set up this project to run on your local machine.

--------------------------------------------------------------------------------
## Requirements
- Java JDK 11
```
https://www.oracle.com/java/technologies/downloads/#java11
```

- STS IDE
```
https://spring.io/tools
```
- Lombok
- Mysql
- Mongodb
- Create two databases, each for mysql and mongodb.


---------------------------------------------------------------------------------

## Project Setup

1. Clone project repository
```
git clone https://github.com/sukhwinder/org-sample_service-eth.git
```

2. Import the folder as gradle project in STS/VSCode or any IDE for Java

3. Update application.properties file at src/main/resources/application.properties with correct configurations or set the below environment value in the machine: 
- MYSQL_URL
- MYSQL_USERNAME
- MYSQL_PASSWORD
- MONGODB_URL
- MONGODB_USERNAME
- MONGODB_PASSWORD

4. Run the application as Spring Boot App.
