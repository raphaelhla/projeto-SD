FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} analise-credito.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "analise-credito.jar"]

