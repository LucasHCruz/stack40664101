package com.cnova.mpschedule.core.job;

import com.cnova.mpschedule.core.integration.BaseClient;
import com.cnova.mpschedule.core.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BaseJob implements Job {

    public static final String URL_DATA = "url";

    @Autowired
    BaseClient baseClient;

    @Autowired
    private Message message;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        String url;
        url = jobExecutionContext.getJobDetail().getJobDataMap().getString(URL_DATA);

        log.info("Chamando a url: {}", url);

        baseClient.call(url);
    }
}
