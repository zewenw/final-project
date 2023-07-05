# How to set up a config client

### Prerequisite
if module or component isn't a client of eureka, should add eureka client dependency first
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

1. pom.xml introduces dependences

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

2. add bootstrap.yml and add following configuration

```yaml
spring:
  cloud:
    config:
      fail-fast: true
      discovery:
        enabled: true
        service-id: config-server
      profile: @environment@


eureka:
  instance:
    instance-id: ${spring.application.name}:${instanceId:${random.value}}}
  client:
    service-url:
      defaultZone: http://127.0.0.1:8999/eureka
```

3. add applicaiton.yml and add following configuration

```yaml
spring:
  application:
    name: test-client
  profiles:
    active: ${spring.profiles.active}
  cloud:
    config:
      enabled: false
```

2. in this scenario, config file name in GitHub repository should be test-client-dev.yml