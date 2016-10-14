package com.cnova.mpschedule.app;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.cnova.mpschedule" })
@EnableAutoConfiguration(exclude = {FreeMarkerAutoConfiguration.class, SecurityAutoConfiguration.class })
public class ModuleConfiguration {
    
}
