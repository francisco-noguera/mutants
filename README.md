# Mutants application V1.0

## Introduction
This document will provide the specification to correctly start the **Mutants** backend application. This application has been build using Java 13, MYSQL 5.7 and Spring Boot 2.3.3. All additional dependencies could be found at **pom.xml**.

## Getting started
To start up Mutants application will be required preinstalled on your local machine or docker container the next software:
- Java 13
- MYSQL 5.7

*Additional Requirements:*

+ If you want to use containerized environments, you will require Docker on your machine to generate the container image, using the provided **DockerFile** inside the project.

## Configuration

To configure the project, is required has a context variables all the variables defined on the **application.yml** inside **./resources** folder of this project. Below you will find a small description for each one of these variables:

${DATABASE_CONNECTION_STRING} – Connection String for MYSQL database.

${DATABASE_USERNAME} – Username to be connected to the database. Please check proper permissions for client application user.

${DATABASE_PASSWORD}: Password for the username provided on the database connection.

## Database creation/restore

Inside **./resources** folder you will find the file **database_schema.sql** with the required queries to create the MYSQL required model for the application.

## Application API documentation

All the API documentation could be found at **/swagger-ui-custom.html**

## Application API usage

Inside **./resources** folder is a **insomnia.json** file that has a API call example that could be imported on insomnia REST application to make API calls on local environment and in a temporal GKE service configured on Google Cloud Platform. Additional you could use the next CURL call as example:

curl --request POST --url http://35.190.173.246:8080/mutants/dna --header 'content-type: application/json' --data '["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]'
curl --request GET  --url http://35.190.173.246:8080/mutants/stats

## Application Errors explanation

For the application has been defined standard error codes, in order to deliver the responsibility to display messages to UI, according to I18N or messages book managing definition. This is the error code explanation

**"500":** Internal server error.

**"60000":** Generic Runtime Application error.

**"60001":** According to application definition, matrix size has to be at least 4x4 to NxN. This error means that, sent matrix has a lower size.

**"60002":** According to application definition, matrix size has to be NxN. The error means that has been send a matrix size NxM.

**"60003":** According to application definition allowed DNA values are A,T,C,G. The error means that there is an invalid value on the provided DNA chain.