
# mp-schedule - CNova

**Documentation:** https://mktplace.atlassian.net/wiki/display/PROJ/MP-SCHEDULE

**Swagger-ui-endpoint**: /swagger-ui.html

## Objective
This project is responsible for schedule services of Marketplace.

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
mvn integration-test -P integration
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

## Jenkins
* [Status dos build e deploy](http://dev.jenkins/view/MONITOR/)
* [Ordem de execução dos jobs](http://dev.jenkins/view/PIPELINES/)
* [Jobs marketplace](http://dev.jenkins/view/Marketplace/)


## Editor Config
http://editorconfig.org/

## Git Ignore
Created by https://www.gitignore.io/api/eclipse,intellij,maven,java

## Banner
[application shell banner](http://patorjk.com/software/taag/#p=display&f=Big%20Money-ne&t=MP-SCHEDULE%0ACNOVA)
