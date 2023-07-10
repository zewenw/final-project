#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

# Add the application's jar to the container
COPY target/authserver.jar authserver.jar

#execute the application:wq

ENTRYPOINT ["java","-jar","/authserver.jar"]

EXPOSE 9000