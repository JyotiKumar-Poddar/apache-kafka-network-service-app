# Spring boot kafka application.

Application to load file to kafka topic and consume it & perform analytics,

## Requirements

* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.3/gradle-plugin/reference/html/) 
* [JDK 11+](https://adoptopenjdk.net/) 
* [Install Docker Compose](https://docs.docker.com/compose/install/)

### Getting Started

To run the application from IDE run main class ApacheKafkaAppApplication.java
or from terminal use
Running test:
```
./gradlew test
```
To run the artifact on terminal using Java:
```
./gradlew clean
java -jar ./build/libs/apache-kafka-network-service-app-0.0.1-SNAPSHOT.jar
```


 # To start the docker image
```
$ docker-compose up -d

For docker cli $ docker compose up --force-recreate 

```
 #To Stop the docker image
```
$ docker-compose stop 

for docker cli $ docker compose stop 
```
# REST endpoint

### Request

`GET /consume/message`

### Response sample

```
{
   "alarmFrequencyData":{
      "ERA015":1
   },
   "nodeAlarmData":{
      "LX00010":1
   },
   "alarmData":{
      "0":0,
      "1":0,
      "2":0,
      "3":0,
      "4":0,
      "5":0,
      "6":0,
      "7":0,
      "8":0,
      "9":0,
      "10":0,
      "11":1,
      "12":0,
      "13":0,
      "14":0,
      "15":0,
      "16":0,
      "17":0,
      "18":0,
      "19":0,
      "20":0,
      "21":0,
      "22":0,
      "23":0
   }
}

```
## React based frontend application to see the graphs

https://github.com/JyotiKumar-Poddar/network-react-app   