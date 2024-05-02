FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} proposta-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Duser.language=pt", "-Duser.country=BR", "-jar", "proposta-app.jar"]
