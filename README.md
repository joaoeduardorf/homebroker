# homebroker

## Pré-requisito

Para o funcionamento do projeto será necessário.

| Java 20 |
| Gradle 7 |
| Mongo 6.0 |

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
 
## Documento Draw.io

O projeto conta com um documento draw.io onde contém uma estruturação de um projeto mais complexo e escalável, além da documentação das classes

## Evolução e ganho de escalabilidade

A entrega desse MVP levou em consideração a possibildade de evlução do projeto e ganho de escalabilidade segmentando as funcionlidades de order, orderbook e wallet, onde a order seria uma api rest com regras simples de validação do input de dados e comunicação com um broker de fila mensageria como Kafka ou RabitMQ por exemplo, o orderbook responsável pelos trades seria segmentado como um consumer dos tópicos da mensageria e existira um consumer para atulizar informações de status de order e valores na wallet.

