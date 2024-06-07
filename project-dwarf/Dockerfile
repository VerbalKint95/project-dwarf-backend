# Utilisez l'image Maven officielle avec JDK 17 pour créer un artefact de build.
# Ceci est une construction multi-étapes.
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
# Copiez uniquement le fichier pom.xml pour télécharger les dépendances Maven
COPY pom.xml .
# Téléchargez les dépendances Maven
RUN mvn dependency:go-offline
# Copiez le reste des sources et compilez l'application
COPY src ./src
RUN mvn package -DskipTests

# Utilisez l'image OpenJDK officielle pour exécuter l'application
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copiez le jar construit à partir de l'étape précédente
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
