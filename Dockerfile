FROM openjdk:8u151-jdk-alpine3.7
  
EXPOSE 8080
 
ENV APP_HOME /usr/src/app

COPY target/secretsanta-0.0.1-SNAPSHOT.jar $APP_HOME/app.jar

USER root
RUN groupadd docker && usermod -aG docker jenkins
USER jenkins

WORKDIR $APP_HOME

ENTRYPOINT exec java -jar app.jar 
