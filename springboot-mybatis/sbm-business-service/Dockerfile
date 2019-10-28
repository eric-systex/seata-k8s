#FROM openjdk:8-jdk-slim
#ENV TZ Asia/Taipei
#VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
#ADD https://projectlombok.org/downloads/lombok.jar lombok.jar
#ENTRYPOINT ["java","-javaagent:/lombok.jar", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#ENTRYPOINT ["java","-javaagent:/lombok.jar", "-Djava.security.egd=file:/dev/./urandom","-cp", "/app.jar ","io.seata.samples.business.SpringbootMybatisBusinessApplication"]

# 选择JDK
FROM openjdk:8u212-jre-slim-stretch
VOLUME /tmp
ADD target/sbm-business-service-1.0.0-SNAPSHOT.jar app.jar
ADD https://projectlombok.org/downloads/lombok.jar lombok.jar
RUN sh -c 'touch /app.jar' && chmod 777 /lombok.jar
ENV JAVA_OPTS="-javaagent:/lombok.jar"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]