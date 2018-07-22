FROM openjdk:9-jre-slim
COPY ./build/libs/ktm-0.0.1_RC.jar /usr/src/ktm/
WORKDIR /usr/src/ktm
EXPOSE 8080
CMD ["java", "-jar", "/usr/src/ktm/ktm-0.0.1_RC.jar"]

#docker build -t ktm-rest .
#docker run -p 8080:8080 ktm-rest
#above two line is incorporated in docker-compose.yml
#need to run only docker-compose up
