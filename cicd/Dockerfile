FROM openjdk:8-jdk-alpine
WORKDIR /
ADD ./target/spotify.jar spotify.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx128m", "-jar","spotify.jar"]
