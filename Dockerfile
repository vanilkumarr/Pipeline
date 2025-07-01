FROM eclipse-temurin:17-jdk-jammy as build

WORKDIR /app
COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
