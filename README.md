# final_project
I want to build the whole base component for most web applicaiton, not only the function component, but also including
CI/CD component, like jenkins server, gitlab. but it depends on how much I can do beacuse there is not enough time for
me to implement the whole picture.
### Architecture
![Alt Text](docs/architecture.jpg)

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

### TimeTable
Sure! Here's the provided schedule formatted as a Markdown table:
- **week 3**
    - [x] spring config
    - [] redis with authorization server and resource server and toolkit

| Week         | Task                                                  | Delivery                       |
|--------------|-------------------------------------------------------|--------------------------------|
| Week 0(12  – 16 June)| Research                                              | Project Description            |
| Week 1(19  – 23 June)| Build base architecture and Integrate OAuth 2.0        |                                |
| Week 2(26  – 30 June)| Integrate Spring Security, PostgreSQL, Redis, and Hibernate | Preliminary Report        |
| Week 3(3  – 7 July)| Integrate Docker and K8S, deploy all components to AWS EC2 as docker image |                                |
| Week 4(10  – 14 July)| Develop a Role-based permission function and build CI/CD pipeline environment (Jenkins) |                                |
| Week 5(17  – 21 July)| Build log collection platform and ElasticSearch, Learn Scrum |                                |
| Week 6(24  – 28 July)| Build Monitor System, develop collect metrics from Reusable Component, and write Interim Report | Interim Report         |
| Week 7(31  – 4 Aug)| Learn ReactJs and develop Front UI                     |                                |
| Week 8(7  – 11 Aug)| Learn ReactJs and develop Front UI                     |                                |
| Week 9(14  – 18 Aug)| Learn ReactJs, develop Front UI, and finalize Report Template | Final Report Template   |
| Week 10(21  – 25 Aug)| Develop Photo-Sharing System                           |                                |
| Week 11(28  – 1 Sep)| Develop Photo-Sharing System                           |                                |
| Week 12(4  – 8 Sep)| Develop Photo-Sharing System and write Final Report     | Final Report and Code          |
| Week 13(11  – 15 Sep)| Viva                                                  |                                |

### Boot Sequence
1. DiscoveryServiceApplication
2. ConfigServerApplication
3. AuthorizationServerApplication
4. GateWayApplication
5. UserManagementApplication
6. Others Server Components
