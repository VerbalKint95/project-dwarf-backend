# Use the official Maven image with JDK 17 to create a build artifact.
# This is a multi-stage build.
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY project-dwarf/pom.xml ./
COPY project-dwarf/src ./src
RUN mvn clean package -DskipTests

# Use the official OpenJDK image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]


