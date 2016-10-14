package com.cnova.mpschedule.app;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.core.FeatureStore;
import org.ff4j.store.FeatureStoreMongoDB;
import org.ff4j.web.ApiConfig;
import org.ff4j.web.FF4JProvider;
import org.ff4j.web.embedded.ConsoleServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.cnova.mpschedule.core.util.helper.FF4JHelper;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

@Configuration
@ComponentScan(basePackages ="org.ff4j.aop")
public class FF4jConfiguration implements FF4JProvider {

    @Autowired
    private Mongo mongo;
 
    @Value("${ff4j.webapi.authentication}")
    private boolean authentication;
    
    @Value("${ff4j.webapi.authorization}")
    private boolean authorization;
    
    @Bean
    public FF4j getFF4j() {
        FF4j ff4j = new FF4j();
        
        //MongoDB
        DBCollection ff4jCollection = mongo.getDB(FF4JHelper.FF4J_DATABASE_NAME).getCollection(FF4JHelper.FF4J_COLLECTION);
        FeatureStore featureStore = new FeatureStoreMongoDB(ff4jCollection);
        ff4j.setFeatureStore(featureStore);
        
        //Create Feature
        createFeatureStore(FF4JHelper.RIGHT_NOW_SERVICE, ff4j, featureStore);
        createFeatureStore(FF4JHelper.TDCA_SERVICE, ff4j, featureStore);

        return ff4j;
    }
    
    private void createFeatureStore(final String nameFeatureStore, final FF4j ff4j, final FeatureStore featureStoreMongoDB) {
        if(!ff4j.getFeatureStore().exist(nameFeatureStore)) {
            featureStoreMongoDB.create(new Feature(nameFeatureStore));
        }
    }
    
    @Bean
    public ApiConfig getApiConfig(FF4j ff4j) {
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setAuthenticate(authentication);
        apiConfig.setAutorize(authorization);
        apiConfig.setFF4j(ff4j);
        return apiConfig;
    }

    @Bean
    public ConsoleServlet getFF4JServlet(FF4j ff4j) {
        ConsoleServlet consoleServlet = new ConsoleServlet();
        consoleServlet.setFf4j(ff4j);
        return consoleServlet;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(ConsoleServlet consoleServlet){
        return new ServletRegistrationBean(consoleServlet, "/ff4j-console/*");
    }
    
}
