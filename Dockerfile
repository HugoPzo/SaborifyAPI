# Usa una imagen base con Java 17
FROM eclipse-temurin:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR compilado al contenedor
COPY target/SaborifyAPI-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto por el que escucha Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
