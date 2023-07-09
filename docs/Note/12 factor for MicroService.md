# 12 Factor of Microservice

### link: https://12factor.net/

- **Code Base** Each microservice should have a single codebase, managed in source control, The code base can have multiple instances of deployment environments such as development, testing, staging, production, and more but is not shared with any other microservices.

<img src="C:\Users\t1551\AppData\Roaming\Typora\typora-user-images\image-20230709103317814.png" alt="image-20230709103317814" style="zoom: 50%;" />

- **Dependencies** 

  - explicitly declare the dependencies your application uses through build tools such as Maven, Gradle(Java), Third-party JAR dependence should be declared using their specific versions number, This allows your microservice to always be built using the same version of libraries.
  - A twelve-factor app never relies on implicit existence of system-wide packages.

  <img src="C:\Users\t1551\AppData\Roaming\Typora\typora-user-images\image-20230709103814448.png" alt="image-20230709103814448" style="zoom:50%;" />

- **Config** Store environment-specific configuration independently from your code. never add embedded configurations to your source code; instead, maintain your configuration completely separated from your deployable microservice. if we keep the configuration packaged within the microservice, we'll need to redeploy each of the hundred instances to make the change.

<img src="C:\Users\t1551\AppData\Roaming\Typora\typora-user-images\image-20230709104309771.png" alt="image-20230709104309771" style="zoom:50%;" />

- **Backing Service**

  - Backing Services best practice indicates that a microservices deploy should be able to swap between local connections to third party without any changes to the application code
  - In the below example, we can see that a local DB can be swapped easily to a third-party DB which is AWS DB here with out any code changes.

  <img src="C:\Users\t1551\AppData\Roaming\Typora\typora-user-images\image-20230709104712951.png" alt="image-20230709104712951" style="zoom:50%;" />

- **Build, Release, Run** Keep your build, release and run stages of deploying your application completely separated. we should be able to build microservices that are independent of the environment which they are running.

<img src="C:\Users\t1551\AppData\Roaming\Typora\typora-user-images\image-20230709105145248.png" alt="image-20230709105145248" style="zoom:50%;" />

- **Processes** 

  - Execute the app as one or more stateless processes, Twelve-factor process are stateless and share nothing, Any data that needs to persist must be stored in a stateful backing service, typically a database. 
  - Microservices can be killed and replaced at any time without the fear that a loss of service-instance will result in data loss.

  <img src="C:\Users\t1551\AppData\Roaming\Typora\typora-user-images\image-20230709105633550.png" alt="image-20230709105633550" style="zoom:50%;" />

- **Port Binding** Web apps are sometimes executed inside a webserver container, for example, PHP apps might run as a module inside Apache HTTPD, or Java apps might run inside Tomcat, but each microservice should be self-contained with its interfaces and functionality exposed on its own port. Doing so provides isolation from other microservices.

- **Concurrency**

  - Services scale out across a large number of small identical process(Copies) as opposed to scaling-up a single large instance on the most powerful machine available.
  - Vertical scaling(Scale Up) refers to increase the hardware infrastructure(CPU, RAM). Horizontal scaling(Scale Out) refers to adding more instances of the application, when you need to scale, launch more microservice instances and scale out and not up.

  <img src="C:\Users\t1551\AppData\Roaming\Typora\typora-user-images\image-20230709110457406.png" alt="image-20230709110457406" style="zoom:50%;" />

- **Disposability** 

  - Service instances should be disposable, favoring fast startups to increase scalability opportunities and graceful shutdowns to leave the system in a correct state. Docker containers along with an orchestrator inherently satify this requirement.
  - For example, if one of the instances of the microservice is failing because of a failure in the underlying hardware, we can shut down the instance without affecting other microservices and start another one somewhere else if needed.

- **Dev/Prod parity**

  - Keep environments across the application lifecycle as similar as possible, avoiding costly shortcuts, Here, the adoption of containers can greatly contribute by promoting the same execution environment.
  - As soon as a code is committed, it should be tested and then promoted as quickly as possible from development all the way to production. this guideline is essential if we want to avoid deployment errors, having similar development and production environments allows us to control all the possible scenarios we might have while deploying and executing our application.

- **Logs**

  - Treat logs generated by microservices as event streams, As logs are written out, they should be managed by tools, such as Logstash that will collect the logs and write them to a central location.
  - The microservice should never be concerned about the mechanisms of how this happens, they only need to focus on writing the log entries into the stdout.

  <img src="C:\Users\t1551\AppData\Roaming\Typora\typora-user-images\image-20230709112101720.png" alt="image-20230709112101720" style="zoom:50%;" />

- **Admin Processes**
  - Run administrative/management tasks as one-off processes. Tasks can include data cleanup and pulling analytics for a report. Tools executing these tasks should be invoked from the production environment, but separately from the application.
  - Developers will often have to do administrative tasks related to their microservices like Data migration, clean up activities. These tasks should never be ad hoc and instead should be done via scripts that are managed and maintained through source code repository. These scripts should be repeatable and non-changing across each environment they're run against. It's important to have defined the types of tasks we need to take into consideration while running our microservice, in case we have multiple microservices with these scripts we are able to execute all of the administrative tasks without having to do it manually.