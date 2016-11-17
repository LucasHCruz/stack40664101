package com.cnova.mpschedule.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = { "com.cnova.mpschedule.core.integration" })
public class FeignConfiguration {
}
