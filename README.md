#Document Conversion API

As the Watson Document Conversion Service will be deprecated, I created a small application which returns plain text from a document, like doc, docx, HTML and pdf

###Build runnable JAR file
mvn package

###Build docker image file
mvn package docker:build

####Run in Docker
docker run -p 8080:8080 -t elstarit/think-spring-boot

####Stop Docker container
docker ps

####Will give you a container id and run

docker stop <containerid>

###Endpoints
**Url**: /api/convert-to-plain-text

**Method**: POST

**Consumes**: File

**Produces**: JSON

```json 
{
    "convertedText": "text",
    "message": "information or error message"
}
```

**Url**: /api/convert-data-to-plain-text

**Method**: POST

**Consumes**: JSON

```json 
{
    "mediaType": "text",
    "data": "base64 encoded representation of text/file"
}
```
**Produces**: JSON

```json 
{
    "convertedText": "text",
    "message": "information or error message"
}
```

###Swagger
I have added Swagger to the application and can be reached via the url /swagger-ui.html
