# Dockerfile
#FROM eclipse-temurin:17-jdk-alpine

#VOLUME /tmp
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar

#ENTRYPOINT ["java", "-jar", "/app.jar"]


FROM maven:3.9.4-eclipse-temurin-17 as dev

WORKDIR /app
COPY . .

CMD ["mvn", "spring-boot:run"]
