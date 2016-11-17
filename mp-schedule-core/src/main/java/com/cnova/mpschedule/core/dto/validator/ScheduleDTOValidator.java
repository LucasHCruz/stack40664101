package com.cnova.mpschedule.core.dto.validator;

import com.cnova.mpschedule.core.dto.ScheduleDTO;
import com.cnova.mpschedule.core.dto.TriggerDTO;
import com.cnova.mpschedule.core.util.Message;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleDTOValidator {

    @Autowired
    Message message;

    @Autowired
    Scheduler scheduler;

    @Autowired
    JobDetailDTOValidator jobDetailDTOValidator;

    @Autowired
    TriggerDTOValidator triggerDTOValidator;

    @Autowired
    CommonsValidator commonsValidator;

    public List<String> validateSchedule(ScheduleDTO schedule) throws SchedulerException {
        List<String> errors = new ArrayList<>();


        if (commonsValidator.objectIsNotNull(schedule, "schedule", errors)) {
            if (commonsValidator.objectIsNotNull(schedule.getJob(), "job", errors)) {
                jobDetailDTOValidator.verifyJobKey(schedule.getJob(), errors);
                jobDetailDTOValidator.verifyIfJobNotExists(schedule.getJob(), errors);
            }

            if (commonsValidator.objectIsNotNull(schedule.getTrigger(), "trigger", errors)) {
                triggerDTOValidator.verifyTriggerKey(schedule.getTrigger(), errors);
                triggerDTOValidator.verifyCronExpression(schedule.getTrigger(), errors);
                triggerDTOValidator.verifyIfTriggerAlreadyExists(schedule.getTrigger(), errors);
            }
        }

        return errors;
    }

    public List<String> validateUnschedule(TriggerDTO trigger) throws SchedulerException {
        List<String> errors = new ArrayList<>();

        if (commonsValidator.objectIsNotNull(trigger, "trigger", errors)) {
            triggerDTOValidator.verifyTriggerKey(trigger, errors);
            triggerDTOValidator.verifyIfTriggerNotExists(trigger, errors);
        }

        return errors;
    }
}
