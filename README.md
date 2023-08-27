# Online Othello Game

## Description

This is an online othello game. It is built using SvelteKit and Spring Boot.

## Requirements

- Java 17
- Maven 3.8.1
- PostgreSQL 13.1.1
- Node.js 18.17.0
- npm 9.6.7

## Installation

1. Clone the repository.
2. Navigate to the back-end directory.
3. Run `mvn install` to install the dependencies.
4. Fill the `application.properties` file with the correct values.
5. Navigate to the front-end directory.
6. Run `npm install` to install the dependencies.
7. Copy the `.env.example` file to `.env` and fill in the values.

## Running for production

1. Navigate to the back-end directory.
2. Run `mvn package` to create a production version of the back end.
3. Run `java -jar target/online-othello-back-end-0.0.1-SNAPSHOT.jar` to start the back end.
4. Navigate to the front-end directory.
5. Run `npm run build` to create a production version of the front end.
