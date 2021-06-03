FROM adoptopenjdk/openjdk11
MAINTAINER aws
COPY target/aws-0.0.1-SNAPSHOT.jar /deployments/
CMD ["java","-jar","/deployments/aws-0.0.1-SNAPSHOT.jar"]
