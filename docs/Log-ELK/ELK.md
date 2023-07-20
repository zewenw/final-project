# Integrate ELK with Springboot Application

[tutorial](https://auth0.com/blog/spring-boot-logs-aggregation-and-monitoring-using-elk-stack/)

1. **Elasticsearch** — Elasticsearch stores and indexes the data. It is a NoSQL database based on Lucene's open-source search engine. Since Elasticsearch is developed using Java, therefore, it can run on different platforms. One particular aspect where it excels is indexing streams of data such as logs.
2. **Logstash** — Logstash is a tool that integrates with a variety of deployments. It is used to collect, parse, transform, and buffer data from a variety of sources. The data collected by Logstash can be shipped to one or more targets like Elasticsearch.
3. **Kibana** — Kibana acts as an analytics and visualization layer on top of Elasticsearch. Kibana can be used to search, view, and interpret the data stored in Elasticsearch.

## Step

1. logback.xml
2. logstash.conf which located in config folder

## How to start on local environment

1. start elasticsearch
2. start kibana
3. start logstash
   1. config file
   2. open powershell and call ` D:\code\logstash\logstash-8.8.2> .\bin\logstash.bat -f .\config\logstash.conf`