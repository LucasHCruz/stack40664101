package com.cnova.mpschedule.core.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BaseJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("BaseJob.execute");
        System.out.println(jobExecutionContext.getTrigger().getKey());

        System.out.println(jobExecutionContext.getJobDetail().getKey().toString());
    }
}
