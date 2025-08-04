# Etapa 1: Build con Gradle
FROM gradle:8.7.0-jdk21 AS builder

COPY --chown=gradle:gradle build.gradle.kts settings.gradle.kts /home/gradle/project/
COPY --chown=gradle:gradle gradle /home/gradle/project/gradle
COPY --chown=gradle:gradle src /home/gradle/project/src

WORKDIR /home/gradle/project

RUN gradle bootJar --no-daemon

# Etapa 2: Imagen final ligera
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
