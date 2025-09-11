# 1. Build stage
FROM gradle:8-jdk17 AS build
WORKDIR /app
COPY src .
RUN gradle clean build -x test

# 2. Run stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy jar từ stage build
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port
EXPOSE 8080

# Run
ENTRYPOINT ["java", "-jar", "app.jar"]
