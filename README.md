# ktm-api App [![Codacy Badge](https://api.codacy.com/project/badge/Grade/8c107175836c470e92353051407c5ab2)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=pradipforever/ktm-api&amp;utm_campaign=Badge_Grade)

ktm-api uses Swagger for documenting the API

**Swagger Configuration Parameters**  
SWAGGER_JSON_URL:   <http://localhost:8080/swagger.json>
SWAGGER_API_URL:    default - <http://localhost:8080/swagger-ui.html>

ktm-api configuration files are hosted on <https://github.com/pradipforever/config-repo> and can 
be accessed via config-server <http://localhost:8888/ktm-rest/default></br>
Sensitive password to access config-server should be passed as an environmental variable for all applications.

<strong>Instruction</strong>
With IntelliJ IDEA up and running, click Import Project on the Welcome Screen, or File | Open on 
the main menu.<br>
In the pop-up dialog make sure to select Gradle's build.gradle file under the complete folder.<br>
IntelliJ IDEA will create a project with all the code from the gradle ready to run.<br>

<strong>To run the app in localhost mode:</strong></br>
Change the ```bootstrap.yml``` property to</br>
```uri: ${SPRING_CONFIG_URI:http://localhost:8888}```</br>
Change the ```ktm-rest-dev.yml``` property to</br>
```url: jdbc:mysql://localhost:33061/ktm?useSSL=false```</br>

Go To:
```KtmApplication.java``` file, and right click on the file.
Select the ```Run KtmApplication``` option, once the app is, it can be accessed on:</br>
<strong>Localhost URL:</strong><br>
http://localhost:8080

<strong>To run the app in the docker</strong></br>
Change the ```bootstrap.yml``` property to</br>
```uri: ${SPRING_CONFIG_URI:<http://docker.for.mac.localhost:8888>}```</br>
Change the ```ktm-rest-dev.yml``` property to</br>
```url: jdbc:mysql://docker.for.mac.localhost:33061/ktm?useSSL=false```</br>

Make sure the docker is installed in the host. In terminal, Run the below command:```docker-compose up```</br>
<strong>Localhost URL: It can be accessed on:</strong><br>
<http://localhost:8080>
