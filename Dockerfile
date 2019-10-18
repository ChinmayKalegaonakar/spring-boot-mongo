FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/muzix-0.0.1-SNAPSHOT.jar app.jar
COPY seed.json ./src/resources/seed.json

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

