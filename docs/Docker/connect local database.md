# Connect to local Redis and database

when we use docker-compose file to start our project on laptop for developing or testing, usually we need to connect local Redis and database such as MySQL or PostgreSQL, so how we config the compose.yml file, following is a example:

```yaml
version: "3.8"

services:

  eurekaserver:
    image: owen8527/eurekaserver:latest
    mem_limit: 700m
    hostname: eurekaserver
    ports:
      - "8999:8999"
    networks:
      - finalproject

  configserver:
    image: owen8527/configserver:latest
    mem_limit: 700m
    hostname: configserver
    ports:
      - "9001:9001"
    depends_on:
      - eurekaserver
    deploy:
      restart_policy:
        condition: on-failure
        delay: 15s
        max_attempts: 3
        window: 120s
    networks:
      - finalproject

  authserver:
    image: owen8527/authserver:latest
    mem_limit: 700m
    hostname: authserver
    ports:
      - "9000:9000"
    depends_on:
      - configserver
      - eurekaserver
    deploy:
      restart_policy:
        condition: on-failure
        delay: 15s
        max_attempts: 3
        window: 120s
    networks:
      - finalproject
    extra_hosts:
      - "local_postgre:10.235.194.152"
      - "local_eureka:10.235.194.152"

networks:
  finalproject:
```

we user extra_hosts to setup the Redis and postgre ip address which refer to laptop IPv4 address. if other service need connect to another service, then we can define the hostname and in our application.yml file , use the hostname instead of ip address to refer to the service we want this application to connect to.

## Modify PostgreSQL conf file

**Error Msg**:

` FATAL: no pg_hba.conf entry for host "10.235.194.152", user "postgres", database "OAuth", no encryption`

**PS**: maybe we also need modify the PostgreSQL configuration file so that PostgreSQL allow connect origin from docker container.

to do this , following indication below:

The error message you encountered suggests that the PostgreSQL server is rejecting the connection from the specified host IP address (`10.235.194.152`) for the `postgres` user and the `OAuth` database due to a missing entry in the `pg_hba.conf` file.

To resolve this issue, you need to add an appropriate entry to the `pg_hba.conf` file on the PostgreSQL server to allow the specified host, user, and database combination to connect. Here's how you can do it:

1. Locate the `pg_hba.conf` file on your PostgreSQL server. It is typically located in the `data` directory of your PostgreSQL installation.

2. Open the `pg_hba.conf` file using a text editor.

3. Add an entry that allows the specified host, user, and database combination to connect. The entry should specify the IP address, authentication method, user, database, and any additional options. For example:

   ```
   shellCopy code# IPv4 local connections for the "postgres" user and "OAuth" database
   host    OAuth    postgres    10.235.194.152/32    md5
   ```

   In this example, `10.235.194.152` is the IP address of the host that needs to connect, `postgres` is the PostgreSQL user, `OAuth` is the database, and `md5` is the authentication method.

   Note: The actual IP address, user, and database values may differ based on your specific setup. Make sure to adjust them accordingly.

4. Save the `pg_hba.conf` file.

5. Restart the PostgreSQL server for the changes to take effect.

After following these steps, the PostgreSQL server should allow connections from the specified host, user, and database combination without the "no pg_hba.conf entry" error.

**How to restart PostgreSQL service on Windows**

- Open the Services Manager by pressing Win + R and typing `services.msc`, then press Enter.
- Locate the PostgreSQL service in the list of services.
- Right-click on the PostgreSQL service and select Restart.



**Pay Attention**: be careful your module name and service id which defined in compose.yml file, they shouldn't contains underscore(_), I encounter some problem and could solve the issue for a whole day, so respect naming convention.