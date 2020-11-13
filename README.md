Add Load testing script


# Welcome

Welcome to Favourite Zoo !!
You know the case study!!!


# TechStack

1.	spring-boot-starter-parent	2.3.5.RELEASE
2.	Open JDK 11
3.	Springdoc Openapi 1.2.32

# Points

1.	It is a SpringBootApplication
2.	Three are 19 end points exposed. 
3.	Exceptions handled are AnimalNotFoundException, RoomNotFoundException, AnimalNotInTheRoomException, FavouriteNotFoundException, ConstraintViolationException and MethodArgumentNotValidException
4.	I didnt palce all the exceptions at the Controller advice, as they are already handled by Specific exception classes
5.	In-Code documentation and namings are made very clear.
6.	Performed Constructor autowiring which is best to maintain immutability
7.	Created separate request POJO for entities to avoid vulnearble injections via entity beans as request "https://rules.sonarsource.com/java/tag/spring/RSPEC-4684"
8.	@ResponseStatus(HttpStatus.OK) is not added for getMappings, because it is a default status code
9.	Since there is only one implementation, I didn't add Service interface
10.	I used nashorn script engine for the mathematical evalautions such as "<=". Please refer FavouriteZooScriptEngine.java
11.	Used lombok across the project to reduce boilerplate code
12.	@ResponseStatus is not added in few controller methods. Means they will return the default status code "OK"
13.	Although the requirement is only for single entries, I used plural names for CRUD operations, as per the standard
14. Custom Input and Output DTOs are used on few scenarios.
15. Input validation is done via javax.validation and hibernate annotations
16.	Animal-Room combinations are made with PatchMapping. Because part of the Animal Entity is updated with rooms field.
17.	List of Happy animals are fetched based on number of animals fall under the rooms side.
18.	UniqueConstraint condition is added for Favourite to allow only valid animal and room ids
19.	For the unique key IDENTITY stratergy is used, because it generates separate sequence for each entity not a shared one.
20.	JpaRepository is used for DB transactios, which made less or no work to write any scripts
21.	I disabled stacktrace to be shown to end user. Comment the following properties in the application properties table. server.error.include-stacktrace
22.	For unittesting, I used simple Junit, supported by SpringBootTest. For JPA, I used @DataJpaTest and for controller class I used Mokito and Spring MockMvc


# OpenApi 3 Specification

This application automatically generated the OpenApi 3 Specification at the "http://localhost:8080/favouritezoo/api-docs".
Please put them in the swagger editor to play with it.


# Dockers

## Run on local docker engine
 
A simple working dockerfile implementation is added as part of this source code. All you need is to run your local Docker engine (Eg:- docker desktop) and execute the following commands sequentially.
*	docker build -t favouritezoo .
*	docker run -p 8080:8080 favouritezoo

If you feel it is working smooth, then
*	docker push jayaprabahar/favouritezoo:latest

## 	Push image into your Dockerhub
*	docker tag favouritezoo jayaprabahar/favouritezoo:latest
*	docker push jayaprabahar/favouritezoo:latest
The push refers to repository [docker.io/jayaprabahar/favouritezoo]
latest: digest: sha256:6847213ec5e98ce26c0f5247f44db356147a2e48e38bff4b9c8619d482d625bf size: 1166

## 	Pull image from my/your Dockerhub

I already pushed the image into my dockerhub. Its a public one. You can pull from three or you can pull from your own docker registry (acr/dockerhub), and run it
*	docker pull jayaprabahar/favouritezoo:latest
*	docker run -p 8080:8080 jayaprabahar/favouritezoo


# Kubernetes & Coud

I added k8s configuration for deployment & service. 

This configuration will work seemlessly in Azure, if you already tagged the image with you AzureContainerRegistry

*	az acr list --resource-group VotingAppResourceGroup --query "[].{acrLoginServer:loginServer}" --output table
*	docker tag favouritezoo <Your_ACR_Login_server>/favouritezoo:latest

Example
*	docker tag favouritezoo jpvotingappacr.azurecr.io/favouritezoo:latest


# Database
As context root is added in the application, all the API endpoints as well as h2-console will be accessed with prefixed context root

*	http://localhost:8080/favouritezoo/h2-console

Please uncomment the "automatically generate SQL schema" configurations in application properties to let spring to automatically generate the SQL schema under the specified location "Uncomment to automatically generate SQL Schema at the specified target"



# Flexibility

1. Application name is added in the application properties to make it suitable to be detected as a microservice with eureka


# Build

```
$ mvn clean install
```

You should able to see the application building, test execution for a total 12 testcases (10 + 1 + 1) and build success

```
$ mvn clean install
[INFO] Scanning for projects...
[INFO]
[INFO] -------------------< com.jayaprabahar:favouritezoo >--------------------
[INFO] Building favouritezoo 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ favouritezoo ---
```

```
[INFO] Results:
[INFO]
[INFO] Tests run: 46, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- maven-jar-plugin:3.2.0:jar (default-jar) @ favouritezoo ---
[INFO] Building jar: C:\Users\x087934\Desktop\favouritezoo\target\favouritezoo-0.0.1-SNAPSHOT.jar
[INFO]
[INFO] --- spring-boot-maven-plugin:2.3.5.RELEASE:repackage (repackage) @ favouritezoo ---
[INFO] Replacing main artifact with repackaged archive
[INFO]
[INFO] --- maven-install-plugin:2.5.2:install (default-install) @ favouritezoo ---
[INFO] Installing C:\Users\x087934\Desktop\favouritezoo\target\favouritezoo-0.0.1-SNAPSHOT.jar to C:\Users\x087934\.m2\repository\com\jayaprabahar\favouritezoo\0.0.1-SNAPSHOT\favouritezoo-0.0.1-SNAPSHOT.jar
[INFO] Installing C:\Users\x087934\Desktop\favouritezoo\pom.xml to C:\Users\x087934\.m2\repository\com\jayaprabahar\favouritezoo\0.0.1-SNAPSHOT\favouritezoo-0.0.1-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  35.861 s
[INFO] Finished at: 2020-11-13T05:13:27+01:00
[INFO] ------------------------------------------------------------------------

```


## Scenarios

### /animals - GET

```
curl -X GET "http://localhost:8080/favouritezoo/animals?offset=0&sort[sorted]=true&sort[unsorted]=true&sort[empty]=true&pageNumber=0&pageSize=0&paged=true&unpaged=true" -H  "accept: */*"
[
  {
    "title": "string",
    "added": "2020-11-13T04:34:52.533Z",
    "located": "2020-11-13T04:34:52.533Z"
  }
]
```

### /animals/{animalId} - GET

```
curl -X GET "http://localhost:8080/favouritezoo/animals/2" -H  "accept: */*"
```

I did not provide all the scenarios, as it is very easy and User friendly to copy the "openapi/favouritezoo-api.yaml" into https://editor.swagger.io/ to play with it.