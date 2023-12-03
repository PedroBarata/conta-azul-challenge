#
# Build stage
#
FROM maven:3.9.5-eclipse-temurin-17-focal AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /target/challenge-0.0.1-SNAPSHOT.jar challenge.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","challenge.jar"]