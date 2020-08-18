# Dockerfile to build API Service Container
############################################################
FROM azul/zulu-openjdk:13.0.4
COPY ./target/Mutants*.jar ./app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080