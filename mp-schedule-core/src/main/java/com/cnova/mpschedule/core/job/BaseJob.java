package com.cnova.mpschedule.core.job;

import com.cnova.mpschedule.core.integration.BaseClient;
import com.cnova.mpschedule.core.util.Message;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseJob implements Job {

    private static final String URL_DATA = "url";

    @Autowired
    BaseClient baseClient;

    @Autowired
    private Message message;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        String url;
        url = jobExecutionContext.getJobDetail().getJobDataMap().getString(URL_DATA);

        System.out.println("Chamando a url: " + url);

        baseClient.call(url);
    }
}
