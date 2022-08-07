FROM openjdk:12.0.2
VOLUME /tmp
ADD /target/springboot-0.0.1-SNAPSHOT app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]