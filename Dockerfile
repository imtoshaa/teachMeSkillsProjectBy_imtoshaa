FROM openjdk:18-alpine
ARG JAR-FILE
COPY target/parts-shop-0.0.1-SNAPSHOT.jar /parts-shop-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/parts-shop-0.0.1-SNAPSHOT.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]