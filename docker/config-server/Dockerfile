FROM openjdk:9-jre-slim

ARG jar_location
ARG ktm_config_jar
ARG ktm_config_jar_path=${jar_location}/${ktm_config_jar}
ARG ktm_config_work_dir
ARG config_repo

WORKDIR ${ktm_config_work_dir}
COPY ${ktm_config_jar_path} ${ktm_config_work_dir}/
VOLUME ${config_repo}

CMD ["java", "-jar", "/usr/src/ktm-config/ktm_config-0.0.1_RC.jar"]

#docker build -t ktm_config .
#docker run -p 8080:8080 ktm_config
#above two line is incorporated in docker-compose.yml
#need to run only docker-compose up
