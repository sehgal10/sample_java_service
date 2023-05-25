FROM gradle:5.2.1-jdk11-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test

FROM openjdk:11-jre-slim
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/org-sample_service-eth-v2023.05.01.jar /app/org-sample_service-eth-v2023.05.01.jar
ENTRYPOINT ["java", "-jar", "/app/org-sample_service-eth-v2023.05.01.jar"]