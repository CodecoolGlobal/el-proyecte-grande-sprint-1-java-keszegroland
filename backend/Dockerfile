# Use an official Maven image as the base image
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build-phase

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests


# Use an official OpenJDK image as the base image
FROM eclipse-temurin:17-jre-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage to the container
COPY --from=build-phase /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

# Set the command to run the application
CMD ["java", "-jar", "app.jar"]