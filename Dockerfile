FROM openjdk:9-jre-slim
ENV jar_location ./build/libs
ENV ktm_rest_jar ktm-rest-0.0.1_RC.jar
ENV work_dir /usr/src/ktm-rest
WORKDIR ${work_dir}
ENV new_jar_location ${work_dir}/${ktm_rest_jar}

COPY ${jar_location}/${ktm_rest_jar} ${work_dir}/
CMD ["java", "-jar", "/usr/src/ktm-rest/ktm-rest-0.0.1_RC.jar"]

#docker build -t ktm-rest .
#docker run -p 8080:8080 ktm-rest
#above two line is incorporated in docker-compose.yml
#need to run only docker-compose up
