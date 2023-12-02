# Sobre

Sistema do desafio do processo para o cargo de Engenheiro de Software na Conta Azul. 

Tem como objetivo controlar robôs em Marte a partir de requisições HTTP.

## Requisitos

* Postman (opcional)
* GraalVM 17.0+

## Stack

O sistema foi feito com base no Java 17+ e framework Spring boot.

* Building
    * [Maven](https://maven.apache.org/guides/index.html): Sistema de build automatizado
* Testing
    * [JUnit 5](https://junit.org/junit5/docs/current/user-guide/): Framework de testes


## Uso

### Executando localmente

Para executar o projeto localmente, pode-se subi-la em sua IDE favorita ou pelo comando:

```
$ ./mvnw spring-boot:run
```

A aplicação possui um [Swagger](https://swagger.io/) no endereço `http://localhost:8080/swagger/index.html`, 
onde existem os _endpoints_ e suas respectivas documentações. Além disso, dentro do projeto, na pasta `src.postman` 
existe uma _collection_ do postman também.

### Rodando os testes

Para rodar a suíte de testes, execute:

```
$ ./mvnw test
```

