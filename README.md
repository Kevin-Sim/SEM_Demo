# SEM_Demo
Week3 Build Status [![Build Status](https://travis-ci.com/Kevin-Sim/SEM_Demo.svg?branch=week3)](https://travis-ci.com/Kevin-Sim/SEM_Demo)


Change MavenMySQL  dependency to latest V8 from V5  
Add <GroupId> to Maven assembly plugin
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

Or if you have mysql on your own machine run image with exposed port -p 33060:3306 and connect directly with?

``mysql -h localhost -P 33060 --protocol=tcp -u root``

Get docker IP
``docker inspect --format '{{ .NetworkSettings.IPAddress }}' <container_id>``

 **Other Useful Commands**

Copy File(s) from container

``docker container cp <containerid>:<path to file> <local filename> or instead of a filename use - to output file to console``

Export whole container as tar

``docker container export <container id>  -o <filename.tgz>``

Create Image from tar

``docker image load -i filename.tgz``

Need to test below ----- Mount a shared directory / volume The example below shared the contents of target with /tmp dir in docker

``docker run -v ./target:/tmp``

Or to mount from docker-compose
```
version: '3'
services:
  # Application Dockerfile is in same folder which is .
  app:
    build: .
    volumes:
      - target:/tmp

  # db is is db folder
  db:
    build: db/.
    command: --default-authentication-plugin=mysql_native_password
    restart: always
    ports:
    - "33060:3306"

volumes:
  target:

```