# Construction du JAR avec maven
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /build

# Copier le fichier pom.xml et télécharger les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline

# Copier le code source et compiler
COPY src ./src
RUN mvn clean package -DskipTests

# Construction de l'application
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copier le jar depuis l'étape de build
COPY --from=builder /build/target/*.jar /app/file-manager.jar

# Définir les volumes pour les données et les logs
VOLUME ["/data", "/config"]

# === Variables d'environnement principales ===
ENV OPENAI_API_KEY=""
ENV INPUT_DIR="/data/input"
ENV OUTPUT_DIR="/data/output"
ENV MANUAL_DIR="/data/manual"

# === PostgreSQL ===
ENV DB_HOST=""
ENV DB_PORT="5432"
ENV DB_NAME=""
ENV DB_USER=""
ENV DB_PASSWORD=""

# Commande de démarrage
ENTRYPOINT ["java", "-jar", "/app/file-manager.jar"]
