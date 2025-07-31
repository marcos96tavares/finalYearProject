FROM eclipse-temurin:17
LABEL authors="vitor"
LABEL maintainer="final@london.met"
WORKDIR /app
COPY target/Client-0.0.1-SNAPSHOT.jar /app/client-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "client-service.jar"]