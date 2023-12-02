# Sobre

Sistema do desafio do processo para o cargo de Engenheiro de Software na Conta Azul. 

Tem como objetivo controlar robôs em Marte a partir de requisições HTTP.

O robô funciona em um plano horizontal, da seguinte maneira:

![img.png](img.png)

O banco de dados é incializado em memória (h2), onde são criados 3 robôs na base de dados no momento 
em que a aplicação sobe:

| id | x_position | y_position | orientation |
|----|------------|------------|-------------|
| 1  | 0          | 0          | N           |
| 2  | 3          | 4          | S           |
| 3  | 1          | 2          | W           |

# Requisitos

* Postman (opcional)
* GraalVM 17.0+

# Stack

O sistema foi feito com base no Java 17+ e framework Spring boot.

* Building
    * [Maven](https://maven.apache.org/guides/index.html): Sistema de build automatizado
* Testing
    * [JUnit 5](https://junit.org/junit5/docs/current/user-guide/): Framework de testes


# Uso

## Executando localmente

Para executar o projeto localmente, pode-se subi-la em sua IDE favorita ou pelo comando:

```
$ ./mvnw spring-boot:run
```

## Banco de dados
Para acessar o banco em memória, basta acessar a url `http://localhost:8080/h2-console/` e os seguintes parâmetros:

```
Driver Classs: org.h2.Driver
JDBC URL: jdbc:h2:mem:testdb
User Name: robot
Password: robot
```


A aplicação possui um [Swagger](https://swagger.io/) no endereço `http://localhost:8080/swagger/index.html`, 
onde existem os _endpoints_ e suas respectivas documentações. Além disso, dentro do projeto, na pasta `src.postman` 
existe uma _collection_ do postman também.

## Rodando os testes

Para rodar a suíte de testes, execute:

```
$ ./mvnw test
```

# Modelo C4

C1:

![img_1.png](img_1.png)

C2:

![img_2.png](img_2.png)

C3: 

![img_3.png](img_3.png)