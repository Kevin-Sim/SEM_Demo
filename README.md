# SEM_Demo
Week2 Build Status [![Build Status](https://travis-ci.com/Kevin-Sim/SEM_Demo.svg?branch=week2)](https://travis-ci.com/Kevin-Sim/SEM_Demo)


Create Bridge
docker network create --driver bridge se-methods

Use bridge put --network se-methods in run options when creating container

Make sure to start mongo image with container name mongo-dbserver 

Problem with maven on some machines

Change the pom.xml single line 

```xml
<artifactId>maven-assembly-plugin</artifactId>
```

To

```xml
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-assembly-plugin</artifactId>
<version>3.3.0</version>

```
