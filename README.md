# SEM_Demo
Week3 Build Status [![Build Status](https://travis-ci.com/Kevin-Sim/SEM_Demo.svg?branch=week3)](https://travis-ci.com/Kevin-Sim/SEM_Demo)


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

