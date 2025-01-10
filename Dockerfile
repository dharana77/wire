FROM openjdk:23-jdk-slim

WORKDIR /app

COPY ./build/libs/wire.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
