# Final Project
Advanced Computer Science Final Project. 

## Aim

Aim to develop a basic development platform so that the developer can leverage this platform to free themselves from complicated functional requirements like monitor, track and scalability etc.

## Motivation

When we build a new project from scratch, except for product requirements, we also need to pay much effort to take care of functional requirements, imaging some scenarios, we release product requirements, but some users complained they encountered some bugs, then what we will do to fix this issue, first we need to locate the problem, so we need a log collection platform, without it, locating problem will become ultra hard. also, due to some promotion campaign, we meet some request peak and our system need to scale up, but we have never spend time on implementing this feature. also, there are many other kind scenarios. so if we have a basic platform and this platform integrate all these features, then things become easier, and this is the purpose of this project.

### The Challenges of Microservices Architecture

1. How to define the right Size for Each Microservice
2. How to distribute the Resource like CPU and RAM
3. How to manage so many microservice
4. How to monitor so many microservice plus its status
5. How to collect all application log and analyse them in a easy and efficient way
6. How to control all the configurations and make them effective as soon as possible without restarting them.
7. How to make our system have availability, scalability
8. How to make our system have resilience & Fault tolerance
9. How to secure our system by using access and permission control
10. How to route our request to different instance.

### TimeTable

**New Features**

- spring config
- swagger
- kubernetes helm
- zipkin
- Webflux Reactive Programming

**To-do Lists**

- automate refresh config
- push Zipkin message synchronized to kafka

| Week                  | Task                                                         | Finished Task | Delivery              |
| --------------------- | ------------------------------------------------------------ | ------------- | --------------------- |
| Week 0(12  – 16 June) | Research                                                     | done          | Project Description   |
| Week 1(19  – 23 June) | Build base architecture and Integrate OAuth 2.0              | done          |                       |
| Week 2(26  – 30 June) | Integrate Spring Security, PostgreSQL, Redis, and Hibernate  | done          | Preliminary Report    |
| Week 3(3  – 7 July)   | Integrate Docker and K8S, deploy all components to AWS EC2 as docker image | done          |                       |
| Week 4(10  – 14 July) | Develop a Role-based permission function and build CI/CD pipeline environment (Jenkins) | done          |                       |
| Week 5(17  – 21 July) | Build log collection platform and ElasticSearch, Learn Scrum | done          |                       |
| Week 6(24  – 28 July) | Build Monitor System, develop collect metrics from Reusable Component, and write Interim Report | done          | Interim Report        |
| Week 7(31  – 4 Aug)   | Learn ReactJs and develop Front UI                           |               |                       |
| Week 8(7  – 11 Aug)   | Learn ReactJs and develop Front UI                           |               |                       |
| Week 9(14  – 18 Aug)  | Learn ReactJs, develop Front UI, and finalize Report Template |               | Final Report Template |
| Week 10(21  – 25 Aug) | Develop Photo-Sharing System                                 |               |                       |
| Week 11(28  – 1 Sep)  | Develop Photo-Sharing System                                 |               |                       |
| Week 12(4  – 8 Sep)   | Develop Photo-Sharing System and write Final Report          |               | Final Report and Code |
| Week 13(11  – 15 Sep) | Viva                                                         |               |                       |

### Architecture
<img src="docs/architecture.jpg" alt="Alt Text" style="zoom:50%;" />

### Tech Stack

| Name           | Website                                  |
|----------------|------------------------------------------|
| Java           | [https://www.java.com/](https://www.java.com/)             |
| Spring         | [https://spring.io/](https://spring.io/)                     |
| Python         | [https://www.python.org/](https://www.python.org/)           |
| Golang         | [https://go.dev/](https://go.dev/)                           |
| OAuth          | [https://oauth.net/2/](https://oauth.net/2/)                 |
| Kafka          | [https://kafka.apache.org/](https://kafka.apache.org/)       |
| Hibernate      | [https://hibernate.org/](https://hibernate.org/)             |
| Django         | [https://www.djangoproject.com/](https://www.djangoproject.com/) |
| Gin            | [https://gin-gonic.com/](https://gin-gonic.com/)             |
| GORM           | [https://gorm.io/](https://gorm.io/)                         |
| Material-UI    | [https://mui.com/](https://mui.com/)                         |
| React Router   | [https://react.dev/](https://react.dev/)                     |
| PostgreSQL     | [https://www.postgresql.org/](https://www.postgresql.org/)   |
| Redis          | [https://redis.io/](https://redis.io/)                       |
| MongoDB        | [https://www.mongodb.com/](https://www.mongodb.com/)         |
| AWS            | [https://docs.aws.amazon.com/index.html](https://docs.aws.amazon.com/index.html) |
| Jenkins        | [https://www.jenkins.io/](https://www.jenkins.io/)           |
| GitHub         | [https://github.com/](https://github.com/)                   |
| Maven          | [https://maven.apache.org/](https://maven.apache.org/)       |
| Docker         | [https://www.docker.com/](https://www.docker.com/)           |
| Kubernetes     | [https://kubernetes.io/](https://kubernetes.io/)             |
| Prometheus     | [https://prometheus.io/](https://prometheus.io/)             |
| Grafana        | [https://grafana.com/](https://grafana.com/)                 |
| ElasticSearch  | [https://www.elastic.co/](https://www.elastic.co/)           |

### Finished Task

- spring security
- spring cloud config
- spring cloud gateway
- spring cloud sleuth

### Boot Sequence

**Preliminary Service**

- Redis
- PostgreSQL
- Zipkin
- 

1. eurekaserver port: 8999

2. configserver port: 9001

3. authserver port: 9000

4. gateway port: 80

5. user port: 10001

6. photo port:10002

7. Others Server Components