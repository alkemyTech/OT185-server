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

### Usuarios 
|  User | Password  | Role  |
| ------------ | ------------ | ------------ |
| dvidela@somosmas.org  | dvidela78 | ADMIN  |
| dsanchez@somosmas.org  | dsanchez  |  ADMIN |
| jsantillan@somosmas.org  | jsantillan  |  ADMIN |
| jrodriguez@somosmas.org | jrodriguez  |  ADMIN |
| jmanera@somosmas.org  |  jmanera54 |  ADMIN |
| mromero@somosmas.org  |  mromero32 |  ADMIN |
| nbrandariz@somosmas.org  |  nbrandariz |  ADMIN |
| nrappoport@somosmas.org  | nrappoport  |  ADMIN |
| sincinga@somosmas.org  | sincinga  |  ADMIN |
| spulido@somosmas.org | spulido14  |  ADMIN |
| icasas@somosmas.org  | icasas99  | USER  |
| scortes@somosmas.org | scortes89  |  USER |
| msanabria@somosmas.org | msanabria  |  USER |
| bcordoba@somosmas.org  | bcordoba  |  USER |
| aaranda@somosmas.org   | aaranda67  |  USER |
| cceballos@somosmas.org  | cceballos  |  USER |
| avillareal@somosmas.org   | avillareal  |  USER |
| clorente@somosmas.org  | clorente  |  USER |
| bvaldez@somosmas.org   | bvaldez95  |  USER |
| dpabon@somosmas.org  | dpabon21  |  USER |
