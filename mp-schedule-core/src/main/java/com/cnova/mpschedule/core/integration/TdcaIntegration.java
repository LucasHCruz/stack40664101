package com.cnova.mpschedule.core.integration;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${mpschedule.uri.tdca}", name = "TdcaIntegration")
public interface TdcaIntegration {

    @RequestMapping(method = RequestMethod.GET, value = "/fake")
    String doFake();
}
