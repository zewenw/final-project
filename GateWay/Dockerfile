#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

#Information around who maintains the image
MAINTAINER eazybytes.com

# Add the application's jar to the container
COPY target/gateway.jar gateway.jar

#execute the application:wq

ENTRYPOINT ["java","-jar","/gateway.jar"]