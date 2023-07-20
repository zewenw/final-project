# basic information about project

## Local Environment

#### Pre-requirements

1. add **auth-server** point to 127.0.0.1 in hosts file
2. `docker run -d -p 9411:9411 openzipkin/zipkin`
3. change active profile to local
4. start redis, postgresql
5. start other service in order

### Desktop k8s Environment

#### Pre-requirements

1. add entry: 127.0.0.1 authserver-service to hosts file
2. comment out eureka config within bootstrap.yml in authserver, gateway, photo and user module
3. change active profile to **k8s**
4. change k8s namespace to **docker-desktop**
5. start redis, postgresql, zipkin separately
6. using `helm install desktop-deployment k8s-local` install all service components



#### Port:

- Eureka: 8999
  - Eureka should be deployed in a cluster mode
  
- ConfigServer: 9001

- Authorization Server: 9000
  - Gateway: 
  
  - dev: 80
  - test: 80
  - prod: 80
  
- User: 10001

- Photo: 10002

