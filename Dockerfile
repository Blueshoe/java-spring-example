# dev stage running with hot-reload
FROM maven:3.8-openjdk-17-slim AS dev
COPY polls/src /app/src
COPY polls/pom.xml /app/pom.xml
WORKDIR /app
ENTRYPOINT ["mvn", "org.springframework.boot:spring-boot-maven-plugin:run"]

# Build stage
FROM dev as build
RUN mvn clean package

# slim production image with only readily packaged jar
FROM openjdk:16-jdk-alpine as production
COPY --from=build /app/target/polls-0.0.2-SNAPSHOT.jar /app/polls-0.0.2-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/polls-0.0.2-SNAPSHOT.jar"]