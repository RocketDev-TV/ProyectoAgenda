FROM maven:3.9-eclipse-temurin-21

WORKDIR /app

# 1. Copiar configuración de dependencias y descargarlas en caché
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 2. Copiar el código fuente y carpetas de la vista
COPY src ./src
# ─── CAMBIA ESTA LÍNEA ───
COPY src/main/webapp ./webapp

EXPOSE 8080

# 3. Ejecutar el código directamente con Maven en modo desarrollo
ENTRYPOINT ["mvn", "spring-boot:run"]