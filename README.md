# Final Project
Advanced Computer Science Final Project. 

## Aim

Aim to develop a basic development platform so that the developer can leverage this platform to free themselves from complicated functional requirements like monitor, track and scalability etc.

## Motivation

When we build a new project from scratch, except for product requirements, we also need to pay much effort to take care of functional requirements, imaging some scenarios, we release product requirements, but some users complained they encountered some bugs, then what we will do to fix this issue, first we need to locate the problem, so we need a log collection platform, without it, locating problem will become ultra hard. also, due to some promotion campaign, we meet some request peak and our system needs to scale up, but we have never spent time on implementing this feature. also, there are many other kinds of scenarios. so if we have a basic platform and this platform integrates all these features, then things become easier, and this is the purpose of this project.

## [Technical Description](Technical Description.md)

### The Challenges of Microservices Architecture and How to Solve It

There are already many out-of-box solutions that can help us solve most of the challenges, but one of them we should deal with on our own, which is **How to make our system have availability, scalability** and **How to make every single component have high performance and low latency**. although the solution is various depending on the product requirement, there are still some common rules we can follow. there it is:

- make every single component stateless, if it's a stateful microservice, try stripping the stateful part of this, and put the data into some cache storage like the Redis cluster
- leverage multi-threading or reactive programming to improve the throughput
- using common design patterns like DLQ to simplify every microservice logic
- using the multi-lever cache, not only Redis, DNS,  database, Nginx, everywhere where we can use cache
- if we extremely pursuing high performance and low latency, give up the transaction and distributed lock, and bear with data inconsistency. but it's just a trade-off based on product requirements.

1. #### How to define the right Size for Each Microservice

   1. **Solution**:
      1. Domain-Driven Design
      2. Brainstorm with domain experts.
   2. **Avoid:**
      1. over design
      2. be realistic(based on your team capability)

2. #### How to distribute the Resource like CPU and RAM

   1. **Solution**:
      1. Containerization&Kubernetes
      2. Serverless

3. #### How to manage so many microservice

   1. **Solution**:
      1. Service Register Service e.g., eureka, zookeeper, nacos etc

4. #### How to monitor so many microservice plus their status

   1. **Solution**:
      1. Prometheus, Zabbix

5. #### How to collect all application logs and analyse them in an easy and efficient way

   1. **Solution**:
      1. ELK

6. #### How to control all the configurations and make them effective as soon as possible without restarting them.

   1. **Solution**:
      1. Configuration Centralized Management by using SpringCloud Config or Nacos

7. #### How to make our system have resilience & Fault tolerance

   1. **Solution**:
      1. Resillence4J, Fail-Fast/Fail-Over logic

8. #### How to secure our system by using access and permission control

   1. **Solution**:
      1. OAuth2.o
      2. Leverage Cyber Security like SSL/TLs, PVC

9. #### How to route our request to a different instance.

   1. **Solution**:
      1. GateWay/DNS/Kubernetes Load Balancer etc

10. #### How to facilitate front-end and back-end API interaction

    1. **Solution**:
       1. Swagger

11. ...
