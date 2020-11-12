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

docker tag lince-open/lince-kvs:latest linceopen/lince-kvs:0.0.9

docker push linceopen/lince-kvs:0.0.9

docker tag lince-open/lince-kvs:latest linceopen/lince-kvs:latest

docker push linceopen/lince-kvs:latest

###GCLOUD

docker tag lince-open/lince-kvs:latest gcr.io/lince-work/lince-kvs:0.0.6

gcloud docker -- push gcr.io/lince-work/lince-kvs:0.0.6

###H2

docker run --name lince-kvs \
-e LINCE_KVS_PORT='8080' \
-e LINCE_KVS_DATASOURCE_URL='jdbc:h2:file:~/lince-kvs' \
-e LINCE_KVS_DATASOURCE_USER='lincekvs' \
-e LINCE_KVS_DATASOURCE_PASS='lincepass' \
-e LINCE_LOG_LEVEL='WARN' \
-p 50001:8080 \
-t lince-open/lince-kvs:latest

###Mysql

docker network create --driver bridge lincenetwork
 
docker run --name lincemysql --network lincenetwork --hostname lincemysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=lince -d mysql:latest

docker run --name lince-kvs \
--network lincenetwork \
-e LINCE_KVS_PORT='8080' \
-e LINCE_KVS_DATASOURCE_URL='jdbc:mysql://lincemysql:3306/lincekvs' \
-e LINCE_KVS_DATASOURCE_USER='lincekvs' \
-e LINCE_KVS_DATASOURCE_PASS='lincepass' \
-e LINCE_KVS_DATASOURCE_DIALECT='org.hibernate.dialect.MySQL5InnoDBDialect' \
-e LINCE_LOG_LEVEL='WARN' \
-p 50001:8080 \
-t linceopen/lince-kvs:latest


####Script
docker exec -it lincemysql mysql -u root -p
 
CREATE USER 'lincekvs'@'%' IDENTIFIED BY 'lincepass';
CREATE DATABASE lincekvs;
GRANT ALL PRIVILEGES ON lincekvs.* TO 'lincekvs'@'%';

## Execução
mvn spring-boot:run

#teste