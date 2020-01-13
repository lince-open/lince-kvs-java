# lince-kvs-java
Microservice que abstrai a persistencia de dados tipo chave-valor. 
Permite o cadastro de dados de cache e banco de dados.

Solução

* Persistencia Spring Data e JPA
* Autenticação com Spring Security e uso de Header
* Testes Unitários com JUnit/Spock/Groove
* Testes Funcionais com JUnit/Spock/Groove
* Swagger2
* Docker

![](https://github.com/lince-open/lince-kvs-java/workflows/Java%20CI/badge.svg)
[![Known Vulnerabilities](https://snyk.io/test/github/lince-open/lince-kvs-java/badge.svg)](https://snyk.io/test/github/pedrozatta/lince-kvs-java)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=lince-open_lince-kvs-java&metric=coverage)](https://sonarcloud.io/dashboard?id=lince-open_lince-kvs-java)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=lince-open_lince-kvs-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=lince-open_lince-kvs-java)


## Docker Hub

https://hub.docker.com/repository/docker/linceopen/lince-kvs

mvn clean package dockerfile:build

docker run --name lince-kvs \
-e LINCE_KVS_PORT='8080' \
-e LINCE_KVS_DATASOURCE_URL='jdbc:h2:file:~/lince-kvs' \
-e LINCE_JAVA_OPT='-Xms64m -Xmx128m' \
-p 50001:8080 \
-t lince-open/lince-kvs:latest


docker tag lince-open/lince-kvs:latest linceopen/lince-kvs:0.0.4

docker push linceopen/lince-kvs:0.0.4


docker tag lince-open/lince-kvs:latest linceopen/lince-kvs:latest

docker push linceopen/lince-kvs:latest

## Execução
mvn spring-boot:run

