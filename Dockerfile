FROM maven:latest
LABEL authors="Veikko, Ilkka, Daniel"

WORKDIR /app

COPY demo/pom.xml .
COPY demo/src ./src

RUN mvn package -DskipTests

CMD ["java", "-jar", "target/demo-1.0-SNAPSHOT.jar"]
