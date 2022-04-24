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
| dvidela@somosmas.org  | dvidela | ADMIN  |
| dsanchez@somosmas.org  | dsanchez  |  ADMIN |
| jsantillan@somosmas.org  | jsantillan  |  ADMIN |
| jrodriguez@somosmas.org | jrodriguez  |  ADMIN |
| jmanera@somosmas.org  |  jmanera |  ADMIN |
| mromero@somosmas.org  |  mromero |  ADMIN |
| nbrandariz@somosmas.org  |  nbrandariz |  ADMIN |
| nrappoport@somosmas.org  | nrappoport  |  ADMIN |
| sincinga@somosmas.org  | sincinga  |  ADMIN |
| spulido@somosmas.org | spulido  |  ADMIN |
| icasas@somosmas.org  | icasas  | USER  |
| scortes@somosmas.org | scortes  |  USER |
| msanabria@somosmas.org | msanabria  |  USER |
| bcordoba@somosmas.org  | bcordoba  |  USER |
| aaranda@somosmas.org   | aaranda  |  USER |
| cceballos@somosmas.org  | cceballos  |  USER |
| avillareal@somosmas.org   | avillareal  |  USER |
| clorente@somosmas.org  | clorente  |  USER |
| bvaldez@somosmas.org   | bvaldez  |  USER |
| dpabon@somosmas.org  | dpabon  |  USER |
