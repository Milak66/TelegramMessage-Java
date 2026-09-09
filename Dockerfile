FROM eclipse-temurin:26-jre

WORKDIR /app

COPY target/server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8666

CMD ["java", "-jar", "app.jar"]