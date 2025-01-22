# Stage 1: Build the Java application
FROM maven:3.8.4-openjdk-11-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and the source code into the container
COPY pom.xml .
COPY src ./src

# Run Maven to build the application and create a JAR file
RUN mvn clean package -DskipTests

# Stage 2: Run the Java application
FROM openjdk:11-jre-slim
# Set the working directory for the final container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/secretsanta-0.0.1-SNAPSHOT.jar /app/secretsanta.jar

# Expose the port that the Java app will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/secretsanta.jar"]
