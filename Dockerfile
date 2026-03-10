# Stage 1: 빌드용 (Gradle로 JAR 생성)
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY gradle gradle
COPY src src
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
RUN chmod +x gradlew
RUN ./gradlew bootJar

# Stage 2: 실행용
FROM eclipse-temurin:17-jdk-alpine

RUN apk add --no-cache tzdata
ENV TZ=Asia/Seoul
ENV JAVA_TOOL_OPTIONS="-Duser.timezone=Asia/Seoul"

WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
COPY files ./files
ENTRYPOINT ["java", "-jar", "app.jar"]
