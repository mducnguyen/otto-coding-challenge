FROM maven:3.6.3-jdk-11 AS MAVEN_BUILD

COPY ./ ./

RUN mvn clean package

FROM openjdk:11-jre

COPY --from=MAVEN_BUILD /target/catalog-filter-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]