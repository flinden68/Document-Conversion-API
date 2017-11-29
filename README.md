# README #

### Document Conversion API ###

As the Watson Document Conversion Service will be deprecated, I created a small application which returns plain text from a document, like doc, docx, HTML and pdf

##Build runnable JAR file
mvn package

##Build docker image file
mvn package docker:build

####Run in Docker
docker run -p 8080:8080 -t elstarit/think-spring-boot

####Stop Docker container
docker ps

####Will give you a container id and run

docker stop <containerid>

