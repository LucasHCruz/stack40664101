package com.cnova.mpschedule.ws.exception.handler;

import com.cnova.mpschedule.core.exception.MpScheduleException;
import com.cnova.mpschedule.ws.dto.ErroDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

	@ResponseBody
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value={MpScheduleException.class})
	public ErroDTO integrationException(HttpServletRequest request, MpScheduleException ex){
		
		String erroMsg = ex.getMessage();
		String url = request.getRequestURL().toString();
		List<String> reasons = ex.getReasons();
		ErroDTO erroDTO = new ErroDTO(erroMsg, url, reasons);
		
		log.info("Erro de negocio da plicação ao chamar a url: {} motivo: {}", url, erroMsg);
		
		return erroDTO;
	}

	@ResponseBody
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value={MethodArgumentNotValidException.class})
	public ErroDTO integrationException(HttpServletRequest request, Exception ex){

		String erroMsg = ex.getMessage();
		String url = request.getRequestURL().toString();
		ErroDTO erroDTO = new ErroDTO(erroMsg, url);

		log.info("Erro de negocio da plicação ao chamar a url: {} motivo: {}", url, erroMsg);

		return erroDTO;
	}
	
}
