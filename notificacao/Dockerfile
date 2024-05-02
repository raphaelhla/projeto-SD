FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} notificacao-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "notificacao-app.jar"]


