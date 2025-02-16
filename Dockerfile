# Use JDK 21
FROM eclipse-temurin:21-jdk as build

# Set the working directory
WORKDIR /app

# Copy the build files
COPY . /app/

# Build the project using Gradle
RUN ./gradlew build

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "build/libs/side-backend-0.0.1.jar"]