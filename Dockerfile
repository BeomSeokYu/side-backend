# Use JDK 21
FROM eclipse-temurin:21-jdk as build

# Set the working directory
WORKDIR /app

# Copy the build files (including Gradle Wrapper)
COPY . /app/

# Grant execute permission for Gradle wrapper
RUN chmod +x gradlew

# Build the project using Gradle
RUN ./gradlew build

# Use a smaller image for the runtime environment (optional but recommended)
FROM eclipse-temurin:21-jdk-slim

# Set the working directory for runtime container
WORKDIR /app

# Copy the built JAR file from the build image
COPY --from=build /app/build/libs/side-backend-0.0.1.jar /app/side-backend.jar

# Set the entry point for the container to run the JAR file
ENTRYPOINT ["java", "-jar", "side-backend.jar"]
