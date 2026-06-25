# Etapa 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
# Descargamos dependencias offline para acelerar futuras construcciones
RUN mvn dependency:go-offline
COPY src ./src
# Compilamos el proyecto saltando los tests para un despliegue rápido
RUN mvn clean package -DskipTests

# Etapa 2: Run
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/sistema-citas-0.0.1-SNAPSHOT.jar app.jar

# Sincronizamos la zona horaria clave para el sistema de citas
ENV TZ="America/Mexico_City"

EXPOSE 8080
ENTRYPOINT ["java", "-Duser.timezone=America/Mexico_City", "-jar", "app.jar"]
