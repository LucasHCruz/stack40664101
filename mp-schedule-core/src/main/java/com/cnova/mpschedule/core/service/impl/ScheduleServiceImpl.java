package com.cnova.mpschedule.core.service.impl;

import com.cnova.mpschedule.core.dto.ScheduleDTO;
import com.cnova.mpschedule.core.dto.TriggerDTO;
import com.cnova.mpschedule.core.exception.MpScheduleException;
import com.cnova.mpschedule.core.service.ScheduleService;
import com.cnova.mpschedule.core.util.Message;
import com.cnova.mpschedule.core.util.helper.FF4JHelper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.matchers.GroupMatcher.groupEquals;


@Slf4j
@Service(FF4JHelper.SCHEDULE_SERVICE)
public class ScheduleServiceImpl implements ScheduleService {

    private final static String URL = "url";

    @Autowired
    Message message;

    @Autowired
    private Scheduler scheduler;

    @Override
    public void schedule(ScheduleDTO schedule) {
        CronTrigger cronTrigger = createTriggerForJob(schedule.getTrigger(), schedule.getJob().getKey());
        try {
            JobDetail jobDetail = this.scheduler.getJobDetail(schedule.getJob().getKey());
            this.scheduler.scheduleJob(cronTrigger);
        } catch (SchedulerException e) {
            String erro = message.getMessage("schedule.fail", schedule.getJob().getKey().toString());

            log.warn(erro, e);
            throw new MpScheduleException(erro);
        }
    }

    @Override
    public void unscheduleJob(TriggerDTO trigger) {
        try {
            this.scheduler.unscheduleJob(trigger.getKey());
        } catch (SchedulerException e) {
            String erro = message.getMessage("schedule.unschedule.fail", trigger.getKey().toString());

            log.warn(erro, e);
            throw new MpScheduleException(erro);
        }
    }

    @Override
    public List<ScheduleDTO> findSchedules() {
        List<ScheduleDTO> schedulesDTO = new ArrayList<>();

        try {
            for(String group: this.scheduler.getTriggerGroupNames()) {
                for(TriggerKey triggerKey : this.scheduler.getTriggerKeys(groupEquals(group))) {
                    CronTrigger trigger = (CronTrigger) this.scheduler.getTrigger(triggerKey);
                    JobKey jobKey = trigger.getJobKey();
                    JobDetail jobDetail = this.scheduler.getJobDetail(jobKey);

                    ScheduleDTO scheduleDTO = new ScheduleDTO(trigger, jobDetail);

                    schedulesDTO.add(scheduleDTO);
                }
            }
        } catch (SchedulerException e) {
            String erro = message.getMessage("schedule.list.fail");

            log.warn(erro, e);
            throw new MpScheduleException(erro);
        }

        return schedulesDTO;
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
