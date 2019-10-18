FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ./target/muzix-0.0.1-SNAPSHOT.jar app.jar
COPY ./src/main/resources/seed.json seed.json
EXPOSE 8102
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]

