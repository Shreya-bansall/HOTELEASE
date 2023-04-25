# Base image
FROM openjdk:8-jdk-alpine

# Set working directory
WORKDIR /app

# Clone the repository
RUN apk update && apk add git
RUN git clone https://github.com/Shreya-bansall/HOTELEASE.git .

# Copy the pom.xml file
COPY pom.xml .

# Build the application
RUN mvn clean install

# Define entrypoint
ENTRYPOINT [ "java", "-jar", "/app/target/myapp.jar" ]
