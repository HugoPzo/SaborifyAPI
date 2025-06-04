# Etapa 1: construir el jar con Maven
FROM maven:3.9.0-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: imagen final con solo JDK (sin Maven, más ligera)
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/SaborifyAPI-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
