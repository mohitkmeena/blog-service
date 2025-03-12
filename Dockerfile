FROM openjdk:21
COPY target/blogservice.jar blogservice.jar
ENTRYPOINT ["java" ,"-jar","/blogservice.jar"]