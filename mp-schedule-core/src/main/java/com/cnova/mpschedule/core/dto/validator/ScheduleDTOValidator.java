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

    public List<String> validateSchedule(ScheduleDTO schedule) throws SchedulerException {
        List<String> errors = new ArrayList<>();

        verifyScheduleFields(schedule, errors);

        if(schedule.getJob() != null) {
            jobDetailDTOValidator.verifyJobKey(schedule.getJob(), errors);
            jobDetailDTOValidator.verifyIfJobExists(schedule.getJob(), errors);
        }

        if(schedule.getTrigger() != null) {
            triggerDTOValidator.verifyTriggerKey(schedule.getTrigger(), errors);
            triggerDTOValidator.verifyCronExpression(schedule.getTrigger(), errors);
            triggerDTOValidator.verifyIfTriggerExists(schedule.getTrigger(), errors);
        }

        return errors;
    }

    public List<String> validateUnschedule(TriggerDTO trigger) throws SchedulerException {
        List<String> errors = new ArrayList<>();

        triggerDTOValidator.verifyTriggerKey(trigger, errors);
        triggerDTOValidator.verifyIfTriggerExists(trigger, errors);

        return errors;
    }

    private void verifyScheduleFields(ScheduleDTO schedule, List<String> errors) {
        if(schedule.getTrigger() == null){
            errors.add(message.getMessage("required.field", "trigger"));
        }
        if(schedule.getJob() == null){
            errors.add(message.getMessage("required.field", "job"));
        }
    }
}
