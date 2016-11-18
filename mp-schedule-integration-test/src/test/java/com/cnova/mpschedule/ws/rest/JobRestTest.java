package com.cnova.mpschedule.ws.rest;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import com.cnova.mpschedule.test.util.IntegrationTestCommon;
import com.jayway.restassured.RestAssured;

public class JobRestTest extends IntegrationTestCommon {
	
	@Value("${local.server.port}")
    private int serverPort;
	
	@Before
    public void setUp() {
        RestAssured.port = serverPort;
	}
	
	@Test
    public void cancelTicket_shouldReturnSuccess() {
        RestAssured
                .given()
                .when()
                .get("job")
                .then().statusCode(HttpStatus.SC_OK);
    }
}
