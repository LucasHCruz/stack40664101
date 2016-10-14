package com.cnova.mpschedule.ws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cnova.mpschedule.core.service.RightNowService;
import com.cnova.mpschedule.core.util.helper.FF4JHelper;



@RestController
@RequestMapping("teste")
public class TestRest {
    
    @Autowired
    @Qualifier(FF4JHelper.RIGHT_NOW_SERVICE)
    private RightNowService apiFrontService;
    
	@RequestMapping(method=RequestMethod.GET,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public String teste(String teste){
		String resultado = "TESTE OK: " + teste + "\n";
		
		resultado += apiFrontService.teste();
		
		return resultado;
	}
	
}
