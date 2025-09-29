FROM maven:latest
LABEL authors="Veikko, Ilkka, Daniel"

WORKDIR /app

COPY demo /app

RUN mvn package

CMD ["java", "-jar", "target/demo-1.0-SNAPSHOT.jar"]
