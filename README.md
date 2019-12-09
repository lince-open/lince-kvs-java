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

## Docker Hub

https://hub.docker.com/repository/docker/linceopen/lince-resource

mvn clean package dockerfile:build

docker run  --name lince-resource -p 8080:8080 -t lince-open/lince-resource:latest

docker tag lince-open/lince-resource:latest linceopen/lince-resource:latest

docker push linceopen/lince-resource:latest

## Execução
mvn spring-boot:run

