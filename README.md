# Welcome

Welcome to Favourite Zoo !!
You know the case study!!!

I used "favourite" which is from American Spelling standard 


# TechStack

1.	spring-boot-starter-parent	2.3.5.RELEASE
2.	Open JDK 11
3.	Springdoc Openapi 1.2.32


# Points

1.	It is a SpringBootApplication
2.	19 EndPoints and 9 Schemas are exposed. 
3.	Exceptions handled are AnimalNotFoundException, RoomNotFoundException, AnimalNotInTheRoomException, FavouriteNotFoundException, ConstraintViolationException and MethodArgumentNotValidException
4.	I didn't place all the exceptions at the Controller advice, as they are already handled by Specific exception classes
5.	In-Code documentation and namings are written clearly.
6.	Performed Constructor autowiring which is best to maintain immutability
7.	Created separate request POJO for entities to avoid vulnearble injections via entity beans as request. Please refer here for more details "https://rules.sonarsource.com/java/tag/spring/RSPEC-4684"
8.	@ResponseStatus(HttpStatus.OK) is not added for getMappings, because it is a default status code
9.	Since there is only one implementation, I didn't add Service interface
10.	I used nashorn script engine for the mathematical evalautions such as "<=". Please refer FavouriteZooScriptEngine.java
11.	Used lombok across the project to reduce boilerplate code
12.	Although the requirement is only for single entries, I used plural names for CRUD operations, as per the standard. Eg:- (animals instead animal)
13. Custom Input and Output DTOs are used on few scenarios.
14. Input validation is done via javax.validation and hibernate annotations
15.	Animal-Room combinations are made with PatchMapping. Because part of the Animal Entity is updated with rooms field.
16.	List of Happy animals are fetched based on number of animals fall under the rooms side.
17.	UniqueConstraint condition is added for Favourite to allow only valid animal and room ids
18.	For the unique key, IDENTITY stratergy is used. Because it generates separate sequence for each entity not a shared one.
19.	JpaRepository is used for DB transactios, which ensures less or no work to write any scripts
20.	I disabled stacktrace to be shown to end user. Comment the following properties in the application properties to view the stacktrace for the exceptions. "server.error.include-stacktrace"
21.	For unittesting, I used simple Junit, supported by SpringBootTest. For JPA, I used @DataJpaTest and for controller class I used Mokito and Spring MockMvc
22. SpringBoots own Pageable sorting option is used. So please use sort=<FIELD_NAME>,<ASC/DESC>, page and offset values for sorting and page level filtering


# OpenApi 3 Specification

This application automatically generates the OpenApi 3 Specification once it is started. Please go to this link 

[http://localhost:8080/favouritezoo/api-docs](http://localhost:8080/favouritezoo/api-docs)

I also placed the same at the following location './openapi/favouritezoo-api.yaml'
Please put either of them in the swagger editor to play with it. [https://editor.swagger.io/](https://editor.swagger.io/)

# Context Root

As context root is added in the application, all the API endpoints as well as h2-console will be accessed with prefixed context root

# Dockers

## Run on local docker engine
 
A simple working Dockerfile implementation is added as part of this source code. All you need is to run your local Docker engine (Eg:- docker desktop) and execute the following commands sequentially.

*	docker build -t favouritezoo .
*	docker run -p 8080:8080 favouritezoo

If you feel it is working smooth, then

*	docker push jayaprabahar/favouritezoo:latest

## 	Pull image from my/your Dockerhub

I already pushed the image into my dockerhub. Its a public one. You can pull from three or you can pull from your own docker registry (acr/dockerhub), and run it

*	docker pull jayaprabahar/favouritezoo:latest
*	docker run -p 8080:8080 jayaprabahar/favouritezoo

## 	Push image into your Dockerhub

Incase if you want to push into your own dockerhub registry, follow the below comments

*	docker tag favouritezoo jayaprabahar/favouritezoo:latest
*	docker push jayaprabahar/favouritezoo:latest

The push refers to repository [docker.io/jayaprabahar/favouritezoo](https://docker.io/jayaprabahar/favouritezoo)
latest: digest: sha256:6847213ec5e98ce26c0f5247f44db356147a2e48e38bff4b9c8619d482d625bf size: 1166

# Kubernetes & Cloud

I added k8s configuration for deployment & service. 

This configuration will work seemlessly in Azure, if you already tagged the image with your AzureContainerRegistry

*	az acr list --resource-group VotingAppResourceGroup --query "[].{acrLoginServer:loginServer}" --output table
*	docker tag favouritezoo <Your_ACR_Login_server>/favouritezoo:latest

Example

*	docker tag favouritezoo jpvotingappacr.azurecr.io/favouritezoo:latest

# Database

As context root is added in the application, all the API endpoints as well as h2-console will be accessed with prefixed context root

*	[http://localhost:8080/favouritezoo/h2-console](http://localhost:8080/favouritezoo/h2-console)

Please uncomment the auto sql generation configurations in application properties to let spring to automatically generate the SQL schema under the specified location "Uncomment to automatically generate SQL Schema at the specified target"


# Flexibility

*	Application name is added in the application properties to make it suitable to be detected as a microservice with eureka


# Build

```
$ mvn clean install
```

You should able to see the application building, test execution for a total 46 testcases and build success

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

## Schemas


```
GenericResponseDto{
	timestamp	string($date-time)
	status	integer($int32)
	error	string
	message	string
}
```
```
AnimalResponseDto {
	title	string
	added	string($date-time)
	located	string($date-time)
}
```
```
AnimalDto {
	title*	string
	type*	string
	preference	integer($int64)
}
```
```
Animal {
	id	integer($int64)
	title*	string
	type*	string
	preference*	integer($int64)
	added	string($date-time)
	located	string($date-time)   
	room	Room {...}
}
```
```
Room {
	id	integer($int64)
	title*	string
	size*	integer($int64)
	created	string($date-time)
	animals	[...]
}
```
```
Favourite {
	id	integer($int64)
	room*	Room {...}
	animal*	Animal {...} 
}
```
```
RoomDto {
	title*	string
	size*	integer($int64)
 
}
```


## Scenarios


##### Animals

* Create animal
```
$ curl -X POST "http://localhost:8080/favouritezoo/animals" -H  "Content-Type: application/json" -d "{\"title\":\"Cow\",\"type\":\"<=\",\"preference\":20}"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   183    0   140  100    43  10000   3071 --:--:-- --:--:-- --:--:-- 14076{"id":3,"title":"Cow","type":"<=","preference":20,"added":"2020-11-13T05:51:51.3857982","located":"2020-11-13T05:51:51.3857982","room":null}
```

* Find animal
```
$ curl -X GET "http://localhost:8080/favouritezoo/animals"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   160    0   160    0     0   3636      0 --:--:-- --:--:-- --:--:--  3720[{"title":"Cow","added":"2020-11-13T05:50:06.058086"},{"title":"Cow","added":"2020-11-13T05:51:48.850876"},{"title":"Cow","added":"2020-11-13T05:51:51.385798"}]
```

* Update animal
```
$ curl -X PUT "http://localhost:8080/favouritezoo/animals/1" -H  "Content-Type: application/json" -d "{\"title\":\"New Cow\",\"type\":\"<=\",\"preference\":100}"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   192    0   144  100    48   3130   1043 --:--:-- --:--:-- --:--:--  4266{"id":1,"title":"New Cow","type":"<=","preference":100,"added":"2020-11-13T05:50:06.058086","located":"2020-11-13T05:53:56.4642369","room":null}
```

* Delete animal
```
$ curl -X DELETE "http://localhost:8080/favouritezoo/animals/1"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    95    0    95    0     0   3958      0 --:--:-- --:--:-- --:--:--  4130{"timestamp":"2020-11-13T05:54:35.067232","status":200,"message":"Animal with id 1 is deleted"}
```


##### Rooms

* Find All Room
```
$ curl -X GET "http://localhost:8080/favouritezoo/rooms"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    87    0    87    0     0   2071      0 --:--:-- --:--:-- --:--:--  2023[{"id":1,"title":"Room","size":20,"created":"2020-11-13T05:49:03.966447","animals":[]}]
```

* Create Room
```
$ curl -X POST "http://localhost:8080/favouritezoo/rooms" -H  "Content-Type: application/json" -d "{\"title\":\"Big Room\",\"size\":35}"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   122    0    92  100    30   6571   2142 --:--:-- --:--:-- --:--:--  9384{"id":2,"title":"Big Room","size":35,"created":"2020-11-13T05:58:05.8759452","animals":null}
```


* find Room by Id
```
$ curl -X GET "http://localhost:8080/favouritezoo/rooms/2"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    89    0    89    0     0   2870      0 --:--:-- --:--:-- --:--:--  2870{"id":2,"title":"Big Room","size":35,"created":"2020-11-13T05:58:05.875945","animals":[]}
```

* Update Room
```
$ curl -X PUT "http://localhost:8080/favouritezoo/rooms/2" -H  "Content-Type: application/json" -d "{\"title\":\"JP Room\",\"size\":10100}"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   123    0    91  100    32   5055   1777 --:--:-- --:--:-- --:--:--  7235{"id":2,"title":"JP Room","size":10100,"created":"2020-11-13T05:58:05.875945","animals":[]}
```

* Delete Room
```
$ curl -X DELETE "http://localhost:8080/favouritezoo/rooms/2"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    94    0    94    0     0   3760      0 --:--:-- --:--:-- --:--:--  3760{"timestamp":"2020-11-13T06:00:19.8682745","status":200,"message":"Room with id 2 is deleted"}
```


##### Animal Room Combinations

* Place Animal in a Room
```
$ curl -X PATCH "http://localhost:8080/favouritezoo/rooms/1/animals/2"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   220    0   220    0     0   5365      0 --:--:-- --:--:-- --:--:--  5500{"id":2,"title":"Cow","type":"<=","preference":20,"added":"2020-11-13T05:51:48.850876","located":"2020-11-13T06:02:48.5770772","room":{"id":1,"title":"Room","size":20,"created":"2020-11-13T05:49:03.966447","animals":[]}}
```

* Move Animal between Rooms
```
$ curl -X PATCH "http://localhost:8080/favouritezoo/rooms/1/animals/2/3"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   224    0   224    0     0  11200      0 --:--:-- --:--:-- --:--:-- 11789{"id":2,"title":"Cow","type":"<=","preference":20,"added":"2020-11-13T05:51:48.850876","located":"2020-11-13T06:04:56.4241757","room":{"id":3,"title":"New Room","size":20,"created":"2020-11-13T06:04:07.798786","animals":[]}}
```


* Delete animal from the room
```
$ curl -X PATCH "http://localhost:8080/favouritezoo/rooms/animals/2"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   138    0   138    0     0   9857      0 --:--:-- --:--:-- --:--:-- 10615{"id":2,"title":"Cow","type":"<=","preference":20,"added":"2020-11-13T05:51:48.850876","located":"2020-11-13T06:05:38.308335","room":null}
```

* List of animals in the room
```
$ curl -X GET "http://localhost:8080/favouritezoo/rooms/1/animals"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100     2    0     2    0     0     42      0 --:--:-- --:--:-- --:--:--    44[]

$ curl -X GET "http://localhost:8080/favouritezoo/rooms/2/animals"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   130    0   130    0     0  10000      0 --:--:-- --:--:-- --:--:-- 10000{"timestamp":"2020-11-13T05:06:27.437+00:00","status":404,"error":"Not Found","message":"","path":"/favouritezoo/rooms/2/animals"}
```


* List of happyAnimals
```
$ curl -X GET "http://localhost:8080/favouritezoo/happyAnimals"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    23    0    23    0     0    479      0 --:--:-- --:--:-- --:--:--   489{"New Room":2,"Room":2}
```


##### Favourie Room Animal Combinations

* Assign Favourite Room for an animal
```
$ curl -X POST "http://localhost:8080/favouritezoo/favourite/rooms/3/animals/2" -d ""
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   253    0   253    0     0  15812      0 --:--:-- --:--:-- --:--:-- 14882{"id":3,"room":{"id":3,"title":"New Room","size":20,"created":"2020-11-13T06:04:07.798786","animals":[]},"animal":{"id":2,"title":"Cow","type":"<=","preference":20,"added":"2020-11-13T05:51:48.850876","located":"2020-11-13T06:05:38.308335","room":null}}


$ curl -X POST "http://localhost:8080/favouritezoo/favourite/rooms/4/animals/2" -d ""
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    94    0    94    0     0   4947      0 --:--:-- --:--:-- --:--:--  4947{"timestamp":"2020-11-13T06:09:47.3043586","status":400,"message":"Information already exist"}
```


* Unassign Favourite Room for an animal
```
$ curl -X DELETE "http://localhost:8080/favouritezoo/favourite/rooms/3/animals/2"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   121    0   121    0     0   7117      0 --:--:-- --:--:-- --:--:--  7117{"timestamp":"2020-11-13T06:10:50.0593964","status":200,"message":"Favourite room is unassigned for room 3 and animal 2"}
```

* Get Assigned Favourite Room 
```
$ curl -X GET "http://localhost:8080/favouritezoo/favourite/animals/2"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    12    0    12    0     0    857      0 --:--:-- --:--:-- --:--:--   923["New Room"]
```
