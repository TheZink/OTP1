FROM maven:latest
LABEL authors="Veikko, Ilkka, Daniel"

WORKDIR /app

COPY demo/pom.xml .
COPY demo/src ./src

RUN mvn -X package

CMD ["java", "-jar", "target/demo-1.0-SNAPSHOT.jar"]
