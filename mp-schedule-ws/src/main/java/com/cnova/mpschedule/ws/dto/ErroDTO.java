package com.cnova.mpschedule.ws.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ErroDTO {

	private String reason;
	private String url;
	private List<String> reasons;
	
	public ErroDTO(String reason, String url, List<String> reasons){
		this.reason = reason;
		this.url = url;
		this.reasons = reasons;
	}

	public ErroDTO(String reason, String url) {
		this.reason = reason;
		this.url = url;
	}
}
