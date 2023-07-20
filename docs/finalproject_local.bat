@echo off

set "redis_path=D:\code\redis\redis-2.8.13\redis-server.exe"
set "elasticsearch_path=D:\code\elasticsearch\elasticsearch-8.8.2\bin\elasticsearch.bat"
set "kibana_path=D:\code\Kibana\kibana-8.8.2\bin\kibana.bat"

start "" docker run -d -p 9411:9411 openzipkin/zipkin
timeout /t 5

start "" "%redis_path%"

timeout /t 5
start "" "%elasticsearch_path%"

timeout /t 5
start "" "%kibana_path%"

timeout /t 5
cd /d D:\code\logstash\logstash-8.8.2
start bin\logstash.bat -f config\logstash.conf

pause
