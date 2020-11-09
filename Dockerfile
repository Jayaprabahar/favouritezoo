FROM openjdk:latest

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} favouritezoo.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/favouritezoo.jar"]