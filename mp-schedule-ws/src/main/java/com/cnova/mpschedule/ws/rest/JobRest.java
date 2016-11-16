package com.cnova.mpschedule.ws.rest;

import com.cnova.mpschedule.core.dto.JobDetailDTO;
import com.cnova.mpschedule.core.service.JobService;
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
@RequestMapping("job")
public class JobRest {

    @Autowired
    @Qualifier(FF4JHelper.JOB_SERVICE)
    private JobService jobService;

    @ApiOperation(
            value = "Find Jobs",
            notes = "Lista os Jobs que estão na store."
    )
    @RequestMapping(method = RequestMethod.GET)
    public List<JobDetailDTO> findJobs() {
        return jobService.findJobs();
    }

    @ApiOperation(
            value = "Register a Job",
            notes = "Registra um novo Job no store de jobs. O job será apenas registrado e não será agendado."
    )
    @RequestMapping(method = RequestMethod.POST)
    public void registerJob(@ApiParam(value = "Job a ser registrado") @RequestBody JobDetailDTO job) {
        jobService.registerJob(job);
    }

    @ApiOperation(
            value = "Update a Job",
            notes = "Atualiza um Job da store de jobs."
    )
    @RequestMapping(method = RequestMethod.PUT)
    public void updateJob(@ApiParam(value = "Job a ser atualizado") @RequestBody JobDetailDTO job) {
        jobService.updateJob(job);
    }

    @ApiOperation(
            value = "Delete a Job",
            notes = "Deleta um Job da store de jobs."
    )
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteJob(@ApiParam(value = "Job a ser deletado") @RequestBody JobDetailDTO job) {
        jobService.deleteJob(job);
    }

    @RequestMapping(path = "teste", method = RequestMethod.GET)
    public void teste(){
        System.out.println("teste");
    }

}
