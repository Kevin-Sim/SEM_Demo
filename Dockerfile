FROM openjdk:latest
COPY ./target/seMethods-0.1.0.1-jar-with-dependencies.jar /tmp
COPY ./.gitignore /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethods-0.1.0.1-jar-with-dependencies.jar"]
