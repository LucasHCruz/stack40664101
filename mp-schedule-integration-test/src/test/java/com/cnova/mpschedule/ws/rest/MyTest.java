package com.cnova.mpschedule.ws.rest;

import com.cnova.mpschedule.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MyTest {


    @Test
    public void imprime(){
        System.out.println("teste");
    }
}
