# Utiliser l'image officielle Java 21
FROM eclipse-temurin:21-jdk

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR généré par Maven
COPY target/*.jar app.jar

# Exposer le port de l'application
EXPOSE 8082

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]