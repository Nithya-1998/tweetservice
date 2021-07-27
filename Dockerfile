FROM openjdk:11
ADD target/tweet-service-0.0.1-SNAPSHOT.jar tweet-service.jar
Expose 8080
ENTRYPOINT ["java","-jar","/tweet-service.jar"]
