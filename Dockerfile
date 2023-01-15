FROM openjdk:17
ENV USE_PROFILE docker
ARG JAR_FILE_PATH=build/libs/*.jar
COPY ${JAR_FILE_PATH} app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${USE_PROFILE}", "app.jar"]