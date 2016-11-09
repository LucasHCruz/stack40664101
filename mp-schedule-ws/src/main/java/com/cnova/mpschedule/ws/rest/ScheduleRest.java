package com.cnova.mpschedule.ws.rest;

import com.cnova.mpschedule.core.dto.ScheduleDTO;
import com.cnova.mpschedule.core.dto.TriggerDTO;
import com.cnova.mpschedule.core.service.ScheduleService;
import com.cnova.mpschedule.core.util.helper.FF4JHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
@RequestMapping("schedule")
public class ScheduleRest {

    @Autowired
    @Qualifier(FF4JHelper.SCHEDULE_SERVICE)
    private ScheduleService scheduleService;

    @ApiOperation(
            value = "Find schedules",
            notes = "Lista os agendamentos."
    )
    @RequestMapping(method = RequestMethod.GET)
    public List<ScheduleDTO> findSchedules() {
        return scheduleService.findSchedules();
    }

    @ApiOperation(
            value = "Schedule a job with a trigger",
            notes = "Agenda um job para ser executado."
    )
    @RequestMapping(method = RequestMethod.POST)
    public void schedule(@ApiParam(value = "Agendamento") @RequestBody ScheduleDTO schedule) {
        scheduleService.schedule(schedule);
    }

    @ApiOperation(
            value = "Unschedule a job for specific trigger",
            notes = "Remove um agendamento de um Job."
    )
    @RequestMapping(method = RequestMethod.DELETE)
    public void unscheduleJob(@ApiParam(value = "Trigger a ser desativada") @RequestBody TriggerDTO trigger){
        scheduleService.unscheduleJob(trigger);
    }
}