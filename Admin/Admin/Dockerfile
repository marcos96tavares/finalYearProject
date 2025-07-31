FROM eclipse-temurin:21-jdk
LABEL authors="vitor"
LABEL maintainer="final@london.met"
WORKDIR /app
COPY target/Admin-0.0.1-SNAPSHOT.jar /app/admin-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "--enable-preview", "-jar", "admin-service.jar"]