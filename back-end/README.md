# Online Othello - Back End

## Description

This is the back end of the online othello game. It is built using Spring Boot.
Based on Restful API, incorporating JWT authentication and Server Sent Events on some endpoints to provide real-time updates.

## Requirements

- Java 17
- Maven 3.8.1
- PostgreSQL 13.1.1

## Installation

1. Clone the repository.
2. Navigate to the back-end directory.
3. Run `mvn install` to install the dependencies.
4. Fill the `application.properties` file with the correct values.

## Running

Run `mvn spring-boot:run` to start the server.

## Testing

Run `mvn test` to run the tests.

## Building

To create a production version of your app:

```bash
mvn package
```
