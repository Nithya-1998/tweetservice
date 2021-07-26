FROM openjdk:11
ADD target/tweet-service-0.0.1-SNAPSHOT.jar tweet-service.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/tweet-service.jar"]
