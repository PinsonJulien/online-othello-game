FROM openjdk:17-jdk-alpine
MAINTAINER pinson.com

ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_DB=postgres
ENV POSTGRES_HOST=localhost
ENV POSTGRES_PORT=5432

RUN apk add --no-cache postgresql
COPY target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]