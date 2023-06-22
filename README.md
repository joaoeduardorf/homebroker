# homebroker

## Pré-requisito

Para o funcionamento do projeto será necessário.

| Java 1.8+ |
| Gradle 3+ |
| Mongo 4.0 |

##### Mongo

Efetuar o download do Mongo, descompactar, acessar a pasta do Mongo, criar a pasta de dados e executar o Mongo.

```bash
mkdir data
cd bin
./mongod --dbpath ../data
```

## Obtendo e configurando o projeto
Obtendo o projeto e acessando a pasta

```bash
git clone https://github.com/joaoeduardorf/homebroker.git
cd homebroker
```

Patindo a pasta raiz do projeto é possível acessar a pasta src/main/resources onde se encontra o arquivo application.properties contendo a configuração do Mongo da aplicação.

```bash
spring.data.mongodb.host=127.0.0.1
spring.data.mongodb.port=27017
spring.data.mongodb.database=homebroker
```
## Executando o projeto

Partindo da pasta raíz do projeto é possível executar o projeto com os comando a seguir:

###### Linux ou Mac
```bash
./gradlew bootJar
./gradlew bootRun
```

###### Windows
```bash
gradlew.bat bootJar
gradlew.bat bootRun
```

## Teste manual

O proejeto com com uma pasta na raíz chamada postman, essa pasta contem um projeto do Postman contendo as principais requisições dispoiníveis para ser executada.

## Teste de carga
 A execução do teste de carga pode ser feita rodando o script de teste na pasta jmeter do projeto, essa pasta contem um arquivo do JMeter e um csv com uma massa de dados.
 

