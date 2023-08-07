# Technical Description

### Architecture

<img src="./docs/architecture.jpg" alt="Alt Text" style="zoom:35%;" />

### What Tech Stack Used

| Name                                                         | Website                                                      |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| Java                                                         | [https://www.java.com/](https://www.java.com/)               |
| Spring Boot<br />Spring Cloud<br />Spring Config<br />Spring Eureka<br />Spring Security<br />Spring Gateway<br />Spring Webflux<br />Spring DataJPA<br />Spring Sleuth | [https://spring.io/](https://spring.io/)                     |
| Python                                                       | [https://www.python.org/](https://www.python.org/)           |
| OAuth                                                        | [https://oauth.net/2/](https://oauth.net/2/)                 |
| Kafka                                                        | [https://kafka.apache.org/](https://kafka.apache.org/)       |
| Hibernate                                                    | [https://hibernate.org/](https://hibernate.org/)             |
| Gin                                                          | [https://gin-gonic.com/](https://gin-gonic.com/)             |
| GORM                                                         | [https://gorm.io/](https://gorm.io/)                         |
| Material-UI                                                  | [https://mui.com/](https://mui.com/)                         |
| React                                                        | [https://react.dev/](https://react.dev/)                     |
| PostgreSQL                                                   | [https://www.postgresql.org/](https://www.postgresql.org/)   |
| Redis                                                        | [https://redis.io/](https://redis.io/)                       |
| MongoDB                                                      | [https://www.mongodb.com/](https://www.mongodb.com/)         |
| AWS                                                          | [https://docs.aws.amazon.com/index.html](https://docs.aws.amazon.com/index.html) |
| Jenkins                                                      | [https://www.jenkins.io/](https://www.jenkins.io/)           |
| GitHub                                                       | [https://github.com/](https://github.com/)                   |
| Maven                                                        | [https://maven.apache.org/](https://maven.apache.org/)       |
| Docker                                                       | [https://www.docker.com/](https://www.docker.com/)           |
| Kubernetes                                                   | [https://kubernetes.io/](https://kubernetes.io/)             |
| Prometheus                                                   | [https://prometheus.io/](https://prometheus.io/)             |
| Grafana                                                      | [https://grafana.com/](https://grafana.com/)                 |
| ElasticSearch                                                | [https://www.elastic.co/](https://www.elastic.co/)           |
| Swagger                                                      | https://swagger.io/                                          |
| Hutool                                                       | https://github.com/dromara/hutool                            |

### Boot Sequence and Description of each Components

1. eurekaserver
   - port: 8999

   - register center

2. configserver
   - port: 9001

   - configuration management center

3. authserver
   - port: 9000

   - authentication&authorization&third party access

4. gateway
   - port: 80

   - distribute request to downstream component

5. user
   - port: 10001
   - user management component
6. photo
   - port:10002
   - business logic/product requirements can be vary depends on your business
7. Others Server Components
   - demonmodule: function programming&spring data jpa code template
   - webflux: reactive programming by using spring webflux and reactor
