# Library Application

## Back-end
Following sections contain details of how to setup and access the back-end of the library application

### Pre-requisites
* Install Java 21 SDK and configure JAVA_HOME, PATH, CLASSPATH etc.
* Install maven and configure MAVEN_HOME, PATH, CLASSPATH etc.

### Running the application
Run the following commands on terminal from root directory (viz. library). It should start tomcat server on port 8080.
```commandline
mvn clean
mvn install 
mvn spring-boot:run
```

### Accessing API Specification (Swagger)
* Documentation is available in HTML/JSON/YAML format
* Make sure the application is running on port 8080
* HTML: [Click to open Swagger-UI](http://localhost:8080/swagger-ui/index.html)
* JSON: [Click to open OpenAPI Description](http://localhost:8080/v3/api-docs)
* YAML: [Click to open OpenAPI Description](http://localhost:8080/v3/api-docs.yaml)

### Testing the application
```commandline
mvn clean test
```
***
## Front-end
The following guides illustrate how to use some features concretely:

### Pre-requisites
* Install Node.js and Node package manager (NPM)

### Running the application
Run the following command on terminal from root directory (viz. library). It should start the app on port 3000.
```commandline
cd app
npm start
```

When started access the application in a browser at [http://localhost:3000](http://localhost:3000)

### Screenshots

Find screen captures of this app in `screenshots` folder/directory. 


### Testing the application
```commandline
cd app
npm test
```
