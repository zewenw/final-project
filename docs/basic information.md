# Basic Information

## Local Environment

#### Pre-requirements

- install PostgreSQL, Redis, ELK, Zipkin, docker
- execute finalproject_local.bat 
- use local profile

### Desktop k8s Environment

#### Pre-requirements

1. add entry: 127.0.0.1 authserver-service to hosts file
2. comment out eureka config within bootstrap.yml in authserver, gateway, photo and user module
3. change active profile to **k8s**
4. change k8s namespace to **docker-desktop**
5. start redis, postgresql, zipkin separately
6. using `helm install desktop-deployment k8s-local` install all service components

#### Port:

- eurekaserver: 8999
  
- configserver: 9001

- authserver: 9000
  
- gateway: 80
  
- user: 10001

- photo: 10002

