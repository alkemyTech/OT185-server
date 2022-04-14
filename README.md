# OT185-JAVA: ONG PROJECT

## Build and Run

Step 1: Build the project.

```sh
mvn clean install -DskipTests
```

Step 2: run on docker mysql.

```sh
docker container run -d --name mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=True -p 3306:3306 mysql
```

Step 3: run the application.

```sh
 mvn spring-boot:run 
```

