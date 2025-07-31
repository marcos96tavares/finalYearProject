FROM eclipse-temurin:21-jdk
LABEL maintainer="final@london.met"
WORKDIR /app
COPY target/LLM-0.0.1-SNAPSHOT.jar /app/llm-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "llm-service.jar"]