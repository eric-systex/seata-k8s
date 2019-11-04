FROM openjdk:8u212-jre-slim-stretch
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ADD https://projectlombok.org/downloads/lombok.jar lombok.jar
RUN touch /app.jar && chmod 644 /lombok.jar
ENV TZ Asia/Taipei
ENTRYPOINT [ "java","-javaagent:/lombok.jar","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar" ]