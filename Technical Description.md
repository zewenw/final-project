# Technical Description

### Architecture

<img src="E:/personal_project/final_project/docs/architecture.jpg" alt="Alt Text" style="zoom:35%;" />

### Tech Stack

| Name          | Website                                                      |
| ------------- | ------------------------------------------------------------ |
| Java          | [https://www.java.com/](https://www.java.com/)               |
| Spring        | [https://spring.io/](https://spring.io/)                     |
| Python        | [https://www.python.org/](https://www.python.org/)           |
| Golang        | [https://go.dev/](https://go.dev/)                           |
| OAuth         | [https://oauth.net/2/](https://oauth.net/2/)                 |
| Kafka         | [https://kafka.apache.org/](https://kafka.apache.org/)       |
| Hibernate     | [https://hibernate.org/](https://hibernate.org/)             |
| Django        | [https://www.djangoproject.com/](https://www.djangoproject.com/) |
| Gin           | [https://gin-gonic.com/](https://gin-gonic.com/)             |
| GORM          | [https://gorm.io/](https://gorm.io/)                         |
| Material-UI   | [https://mui.com/](https://mui.com/)                         |
| React Router  | [https://react.dev/](https://react.dev/)                     |
| PostgreSQL    | [https://www.postgresql.org/](https://www.postgresql.org/)   |
| Redis         | [https://redis.io/](https://redis.io/)                       |
| MongoDB       | [https://www.mongodb.com/](https://www.mongodb.com/)         |
| AWS           | [https://docs.aws.amazon.com/index.html](https://docs.aws.amazon.com/index.html) |
| Jenkins       | [https://www.jenkins.io/](https://www.jenkins.io/)           |
| GitHub        | [https://github.com/](https://github.com/)                   |
| Maven         | [https://maven.apache.org/](https://maven.apache.org/)       |
| Docker        | [https://www.docker.com/](https://www.docker.com/)           |
| Kubernetes    | [https://kubernetes.io/](https://kubernetes.io/)             |
| Prometheus    | [https://prometheus.io/](https://prometheus.io/)             |
| Grafana       | [https://grafana.com/](https://grafana.com/)                 |
| ElasticSearch | [https://www.elastic.co/](https://www.elastic.co/)           |

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
