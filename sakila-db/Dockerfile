FROM mysql
# executed in alphabetical order
COPY *.sql /docker-entrypoint-initdb.d/
ENV MYSQL_ROOT_PASSWORD example

# or start container with -e MYSQL_ROOT_PASSWORD=example -p 33070:3306

