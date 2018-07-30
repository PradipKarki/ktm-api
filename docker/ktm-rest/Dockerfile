FROM openjdk:9-jre-slim

ARG jar_location
ARG ktm_rest_jar
ARG ktm_rest_jar_path=${jar_location}/${ktm_rest_jar}
ARG ktm_rest_work_dir

WORKDIR ${ktm_rest_work_dir}
ADD ${ktm_rest_jar_path} ${ktm_rest_work_dir}/
CMD ["java", "-jar", "/usr/src/ktm-rest/ktm_rest-0.0.1_RC.jar"]

#docker build -t ktm-rest .
#docker run -p 8080:8080 ktm-rest
#above two line is incorporated in docker-compose.yml
#need to run only docker-compose up
#RUN mkdir /opt/cals-api
#RUN mkdir /opt/cals-api/logs