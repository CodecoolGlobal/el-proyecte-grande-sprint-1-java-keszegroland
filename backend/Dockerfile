FROM maven:3.9.6-eclipse-temurin-17-alpine AS build-phase

WORKDIR /app

# COPY pom.xml .
# COPY src ./src
COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build-phase /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]