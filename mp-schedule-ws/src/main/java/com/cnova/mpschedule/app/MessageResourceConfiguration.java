package com.cnova.mpschedule.app;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageResourceConfiguration {
    
    @Value("${messages.basename}")
    private String messageBaseName;

    @Value("${messages.default.country}")
    private String country;

    @Value("${messages.default.language}")
    private String language;
    
    @Value("${messages.default.encoding}")
    private String encoding;
    
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(new Locale(this.language, this.country));
        return resolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(this.messageBaseName);
        messageSource.setDefaultEncoding(this.encoding);
        messageSource.setFallbackToSystemLocale(true);
        return messageSource;
    }

}
