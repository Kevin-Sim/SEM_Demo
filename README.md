# SEM_Demo
Week2 Build Status [![Build Status](https://travis-ci.com/Kevin-Sim/SEM_Demo.svg?branch=week2)](https://travis-ci.com/Kevin-Sim/SEM_Demo)

**Run mongo db in docker and connect directly by running App**

Start mongo by creating a container with `-p 27000:27017`

Add dependencies to maven

```xml
 <dependencies>
    <dependency>
        <groupId>org.mongodb</groupId>
        <artifactId>mongodb-driver</artifactId>
        <version>3.6.4</version>
    </dependency>
</dependencies>
```

Connect in App (not from docker just run app) using connection

`MongoClient mongoClient = new MongoClient("localhost", 27000);`

**Create Bridge for 2 docker containers to talk to each other Also change Maven to build to self contained jar**

`docker network create --driver bridge se-methods`

Restart mongo on network (delete all old containers) recreate mongo container using 
bridge put `--network se-methods` in run options when creating container and Make sure 
to start mongo image with container name `mongo-dbserver` 

Change App connection string to 

`MongoClient mongoClient = new MongoClient("mongo-dbserver");`

Change maven build to package with dependencies

```xml
<build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>3.3.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.napier.sem.App</mainClass>
                        </manifest>
                    </archive>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

``` 

Change Dockerfile to use the jar

```dockerfile
FROM openjdk:latest
COPY ./target/seMethods-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethods-0.1.0.1-jar-with-dependencies.jar"]
```

Build the image (don't run)

start container with `--network se-methods` 

**Update .travis.yml to use bridge** 

```dockerfile
sudo: required

language: java

services:
  - docker

after_success:
  - docker network create --driver bridge se-methods
  - docker pull mongo
  - docker run -d --name mongo-dbserver --network se-methods mongo
  - docker build -t se_methods .
  - docker run --network se-methods se_methods
```
**Problem with pom code for lab 2 maven on some machines**

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
