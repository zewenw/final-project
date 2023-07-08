#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

#Information around who maintains the image
MAINTAINER eazybytes.com

# Add the application's jar to the container
COPY target/discovery-service.jar discovery-service.jar

EXPOSE 8999

#execute the application:wq

ENTRYPOINT ["java","-jar","/discovery-service.jar"]