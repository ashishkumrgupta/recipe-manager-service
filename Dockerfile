#FROM adoptopenjdk/openjdk11:alpine-jre
#COPY target/recipe-manager-api-0.0.1-SNAPSHOT.jar recipe-manager-api-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","/recipe-manager-api-0.0.1-SNAPSHOT.jar"]


FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/recipe-manager-api-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]