# Stage 1: Build the application using Gradle
FROM gradle:8.4.0-jdk17 AS build

# Copy all files
COPY --chown=gradle:gradle . /app

WORKDIR /app

# Build the project
RUN gradle build --no-daemon

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy jar from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
