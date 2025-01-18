FROM maven:3.9-eclipse-temurin-23 AS build
WORKDIR /
COPY /src /src
COPY pom.xml /
RUN mvn -f /pom.xml clean package -DskipTests

FROM eclipse-temurin:23-jdk
WORKDIR /
COPY --from=build /target/*.jar uses-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "uses-service.jar"]
