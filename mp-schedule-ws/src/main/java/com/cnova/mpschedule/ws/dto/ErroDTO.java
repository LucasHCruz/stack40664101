package com.cnova.mpschedule.ws.dto;

import lombok.Data;

@Data
public class ErroDTO {

	private String reason;
	private String url;
	
	public ErroDTO(){
		//No-op
	}
	
	public ErroDTO(String reason, String url){
		this.reason = reason;
		this.url = url;
	}
}
