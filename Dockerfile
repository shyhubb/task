FROM maven:3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/task-0.0.1-SNAPSHOT.war task.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "task.war"]
