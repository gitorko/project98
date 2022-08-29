# Project 98

Spring Boot & Ehcache

[https://gitorko.github.io/spring-ehcache/](https://gitorko.github.io/spring-ehcache/)

### Version

Check version

```bash
$java --version
openjdk 17.0.3 2022-04-19 LTS
```

### Postgres DB

```
docker run -p 5432:5432 --name pg-container -e POSTGRES_PASSWORD=password -d postgres:9.6.10
docker ps
docker exec -it pg-container psql -U postgres -W postgres
CREATE USER test WITH PASSWORD 'test@123';
CREATE DATABASE "test-db" WITH OWNER "test" ENCODING UTF8 TEMPLATE template0;
grant all PRIVILEGES ON DATABASE "test-db" to test;

docker stop pg-container
docker start pg-container
```

### Dev

To run the backend in dev mode.

```bash
./gradlew clean build
./gradlew bootRun
```

### Postman

Import the postman collection to postman

[Postman Collection](https://raw.githubusercontent.com/gitorko/project98/main/postman/Project98.postman_collection.json)

