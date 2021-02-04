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

