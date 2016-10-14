package com.cnova.mpschedule.app;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = { "com.cnova.mpschedule.core.repository" })
public class MongoConfiguration {
    
}
