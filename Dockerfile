FROM openjdk:9-jre-slim
COPY ./target/ktm-0.0.1-SNAPSHOT.jar /usr/src/ktm/
WORKDIR /usr/src/ktm
EXPOSE 8080
CMD ["java", "-jar", "ktm-0.0.1-SNAPSHOT.jar"]


####./mvnw package && java -jar target/ktm-0.0.1-SNAPSHOT.jar
####docker build -t ktm-build .
####docker run -p 8080:8080 ktm-build
#################above two line is incorporated in docker-compose.yml, need now only one below command
####docker-compose up


#####docker system prune -a
####docker volume prune