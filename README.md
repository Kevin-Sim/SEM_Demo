# SEM_Demo
Week3 Build Status [![Build Status](https://travis-ci.com/Kevin-Sim/SEM_Demo.svg?branch=week3)](https://travis-ci.com/Kevin-Sim/SEM_Demo)


Change MavenMySQL  dependency to latest V8 from V5  
Change The single line for the Maven assembly plugin to the three lines below (solves problems for some students)
```xml
<groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>3.3.0</version>
```

Change docker-compose add the following at the end of the db build so we can access locally on port 33060
```
ports:
- "33060:3306"
```
We Can then use MySQL Workbench to connect and run queries

Or change the connection string in App.java to access locally without running our code inside docker

**Running Locally Not In Docker**

``con = DriverManager.getConnection("jdbc:mysql://localhost:33060/employees?useSSL=true", "root", "example");``

**Running Inside Docker Using docker-compose**

``con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?useSSL=false", "root", "example");``

**Alternatively** 

Connect to running container 

``docker exec -it <Container ID> /bin/bash``

Inside the MySql Docker Container Connect to The employees Db using default username and password (note no space after -p) 

``mysql -u root -pexample employees``

Run a SQL query 

``Select count(*) FROM employees;`` 

**Other Useful Commands**

Get docker IP
``docker inspect --format '{{ .NetworkSettings.IPAddress }}' <container_id>``

 Copy File(s) from container

``docker container cp <containerid>:<path to file> <local filename> or instead of a filename use - to output file to console``

Export whole container as tar

``docker container export <container id>  -o <filename.tgz>``

Create Image from tar

``docker image load -i filename.tgz``for debugging locally

Mount a shared directory / volume The example below shares the contents of target with /tmp dir in docker which allows us to repackage the jar without needing to rebuild the app container. We can then leave the db running and remove the delay from App  

**Remember to add the delay back into the app code so that it will work on travis**

``docker run -v .\target:/tmp``

Or to mount from docker-compose
```
version: '3'
services:
  # Application Dockerfile is in same folder which is .
  app:
    build: .
    volumes:
      - .\target:/tmp

  # db is is db folder
  db:
    build: db/.
    command: --default-authentication-plugin=mysql_native_password
    restart: always
    ports:
    - "33060:3306"

```