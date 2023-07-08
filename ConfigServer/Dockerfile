#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

#Information around who maintains the image
MAINTAINER eazybytes.com

# Add the application's jar to the container
COPY target/config-server.jar config-server.jar

#execute the application:wq

ENTRYPOINT ["java","-jar","/config-server.jar"]