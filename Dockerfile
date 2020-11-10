FROM openjdk:latest

COPY  ./target/*favouritezoo*.jar favouritezoo.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/favouritezoo.jar"]