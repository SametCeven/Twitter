# Dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /twitter
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]


