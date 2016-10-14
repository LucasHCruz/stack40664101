package com.cnova.mpschedule.ws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cnova.mpschedule.core.integration.ApiFrontIntegration;


@RestController
@RequestMapping("/testIntegration")
public class TestIntegrationRest {

    @Autowired
    ApiFrontIntegration apiFrontIntegration;

    @RequestMapping(method = RequestMethod.GET, value = "/internal")
    public String testInternal(){
        return "Internal test!!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/external")
    public String testExternal(){
        return apiFrontIntegration.doFake();
    }
}
