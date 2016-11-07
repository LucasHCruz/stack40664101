package com.cnova.mpschedule.ws.rest;

import com.cnova.mpschedule.core.service.ScheduleService;
import com.cnova.mpschedule.core.util.helper.FF4JHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("jobs")
public class ScheduleRest {
    
    @Autowired
    @Qualifier(FF4JHelper.SCHEDULE_SERVICE)
    private ScheduleService scheduleService;
    
	@RequestMapping(method=RequestMethod.GET,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public String createTrigger(/*@RequestBody TriggerDTO trigger*/){
		/*TriggerDTO triggerDTO = new TriggerDTO();
		triggerDTO.setCronExpression("0/5 * * * * ?");
		triggerDTO.setTriggerName("quinze-segundos-trigger");
		triggerDTO.setGroupName("grupo-teste");

		JobDetailDTO jobDetailDTO = new JobDetailDTO();
		jobDetailDTO.setGroupName("grupo-teste");
		jobDetailDTO.setJobName("teste");
		jobDetailDTO.setUrl("http://10.128.135.30:7300/health");

		scheduleService.registerJob(jobDetailDTO);

		ScheduleDTO scheduleDTO = new ScheduleDTO();

		scheduleDTO.setTrigger(triggerDTO);
		scheduleDTO.setJobDetail(jobDetailDTO);

		scheduleService.schedule(scheduleDTO);*/

		return null;
	}
	
}
