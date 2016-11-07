package com.cnova.mpschedule.core.service.impl;

import com.cnova.mpschedule.core.dto.JobDetailDTO;
import com.cnova.mpschedule.core.dto.ScheduleDTO;
import com.cnova.mpschedule.core.dto.TriggerDTO;
import com.cnova.mpschedule.core.exception.MpScheduleException;
import com.cnova.mpschedule.core.job.BaseJob;
import com.cnova.mpschedule.core.service.ScheduleService;
import com.cnova.mpschedule.core.util.Message;
import com.cnova.mpschedule.core.util.helper.FF4JHelper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


@Slf4j
@Service(FF4JHelper.SCHEDULE_SERVICE)
public class ScheduleServiceImpl implements ScheduleService {

    private final static String URL = "url";

    @Autowired
    Message message;

    @Autowired
    private SchedulerFactoryBean scheduler;

    @Override
    public JobDetail registerJob(JobDetailDTO job) {
        JobDetail jobDetail = newJob(BaseJob.class)
                .withIdentity(job.getJobName(), job.getGroupName())
                .usingJobData(URL, job.getUrl())
                .build();

        try {
            this.scheduler.getScheduler().addJob(jobDetail, true, true);
            //this.scheduler.getScheduler().getTrigger(null);
        } catch (SchedulerException e) {
            String erro = message.getMessage("job.create.fail", jobDetail.getKey().toString());
            log.warn(erro, e);
            throw new MpScheduleException(erro);
        }
        return jobDetail;
    }

    @Override
    public void schedule(ScheduleDTO schedule) {
        CronTrigger cronTrigger = createTriggerForJob(schedule.getTrigger(), schedule.getJobDetail().getKey());
        try {
            JobDetail jobDetail = this.scheduler.getScheduler().getJobDetail(schedule.getJobDetail().getKey());
            this.scheduler.getObject().scheduleJob(cronTrigger);
        } catch (SchedulerException e) {
            String erro = message.getMessage("schedule.fail", schedule.getJobDetail().getKey().toString());

            log.warn(erro, e);
            throw new MpScheduleException(erro);
        }
    }

    private CronTrigger createTriggerForJob(TriggerDTO trigger, JobKey jobKey) {
        CronTrigger cronTrigger = newTrigger()
                .withIdentity(trigger.getTriggerName(), trigger.getGroupName())
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(trigger.getCronExpression())
                )
                .forJob(jobKey)
                .build();

        return cronTrigger;
    }
}
