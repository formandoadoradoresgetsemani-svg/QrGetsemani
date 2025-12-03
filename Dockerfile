# Dockerfile
# Etapa 1: Build con Maven
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copiar archivos de configuración Maven
COPY pom.xml .

# Descargar dependencias (cache para builds más rápidos)
RUN mvn dependency:go-offline

# Copiar código fuente
COPY src ./src

# Compilar y empaquetar
RUN mvn clean package -DskipTests

# Etapa 2: Runtime con JRE más pequeño
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiar el JAR desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponer puerto (Render usa 10000)
EXPOSE 10000

# Variables de entorno para Spring
ENV PORT=10000
ENV SPRING_PROFILES_ACTIVE=production

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
