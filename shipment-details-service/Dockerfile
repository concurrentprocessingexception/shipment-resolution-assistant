# Use OpenJDK base image
FROM openjdk:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy JAR to container
COPY target/*.jar app.jar

# Expose port 8089
EXPOSE 8089

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]