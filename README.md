**Swagger-ui-endpoint**: /swagger-ui.html

## Objective
This project is responsible for schedule services.

## Tecnologies
* SpringCloud
* SpringBoot
* SpringData
* Feign
* FF4J
* MongoDB
* QueryDSL
* Swagger
* Fixture Factory
* Lombok
* JGit-Flow
* Slf4j
* Quartz

## MongoDB Configuration
```javascript

	use mp-schedule;
	db.createUser({
	  user: "mongo_mp_schedule",
	  pwd: "123",
	  roles: [ { role: "userAdmin", db: "mp-schedule" } ]
	});
	
	use mp-schedule-itest;
	db.createUser({
	  user: "mongo_mp_schedule",
	  pwd: "123",
	  roles: [ { role: "userAdmin", db: "mp-schedule-itest" } ]
	});
```

## Run Integration Test
```shell
mvn clean install -P integration
```


## GIT Flow
```shell
mvn jgitflow:feature-start
mvn jgitflow:feature-finish

mvn jgitflow:hotfix-start
mvn jgitflow:hotfix-finish

mvn jgitflow:release-start
mvn jgitflow:release-finish
```

## Editor Config
http://editorconfig.org/

## Git Ignore
Created by https://www.gitignore.io/api/eclipse,intellij,maven,java

## Banner
[application shell banner](http://patorjk.com/software/taag/#p=display&f=Big%20Money-ne&t=MP-SCHEDULE%0ACNOVA)
