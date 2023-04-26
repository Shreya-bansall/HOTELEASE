# Use a suitable base image with Maven installed
FROM maven:3.8.4-openjdk-8-slim AS build

# Set working directory
WORKDIR /app

# Clone the repository
RUN apt-get update && apt-get install -y git
RUN git clone https://github.com/Shreya-bansall/HOTELEASE.git .

# Copy the pom.xml file
COPY pom.xml .

# Build the application
RUN mvn clean install

# Switch to a smaller base image for runtime
FROM openjdk:8-jre-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/myapp.jar .

# Define entrypoint
ENTRYPOINT [ "java", "-jar", "/app/myapp.jar" ]
