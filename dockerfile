# Use an official Maven runtime as a base image
FROM maven:3.8.4-openjdk-11-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml .

# Download and install the Maven dependencies
RUN mvn dependency:go-offline

# Copy the application source code to the container
COPY src ./src

# Build the application using Maven
RUN mvn clean install
