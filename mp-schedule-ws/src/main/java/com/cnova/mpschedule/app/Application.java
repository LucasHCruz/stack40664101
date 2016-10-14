package com.cnova.mpschedule.app;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {
    
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class, 
                ModuleConfiguration.class,
                MongoConfiguration.class,
                SwaggerConfiguration.class,
                MessageResourceConfiguration.class,
                FeignConfiguration.class,
                FF4jConfiguration.class
                ).run(args);
    }
}
