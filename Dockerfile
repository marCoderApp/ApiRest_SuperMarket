FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/ApiRestSupermaket-0.0.1.jar
COPY ${JAR_FILE} app_apiSuperMarket.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_apiSuperMarket.jar"]
