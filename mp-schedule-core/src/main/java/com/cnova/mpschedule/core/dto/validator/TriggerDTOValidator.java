package com.cnova.mpschedule.core.dto.validator;

import com.cnova.mpschedule.core.dto.TriggerDTO;
import com.cnova.mpschedule.core.util.Message;
import com.google.common.base.Strings;
import org.quartz.CronExpression;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TriggerDTOValidator {

    @Autowired
    Message message;

    @Autowired
    Scheduler scheduler;

    protected void verifyTriggerKey(TriggerDTO triggerDTO, List<String> errors){

        if(Strings.isNullOrEmpty(triggerDTO.getTriggerName())){
            errors.add(message.getMessage("required.field", "triggerName"));
        }

        if(Strings.isNullOrEmpty(triggerDTO.getGroupName())){
            errors.add(message.getMessage("required.field", "groupName"));
        }
    }

    protected void verifyIfTriggerExists(TriggerDTO triggerDTO, List<String> errors) throws SchedulerException {
        if(Strings.isNullOrEmpty(triggerDTO.getGroupName()) || Strings.isNullOrEmpty(triggerDTO.getTriggerName())){
            //não adiciona nenhum erro, porque o verifyTriggerKey() já adiciona os erros na lista.
            return;
        }
        if(!scheduler.checkExists(triggerDTO.getKey())){
            errors.add(message.getMessage("trigger.not.exists", triggerDTO.getKey().toString()));
        }
    }

    protected void verifyCronExpression(TriggerDTO triggerDTO, List<String> errors){
        if(Strings.isNullOrEmpty(triggerDTO.getCronExpression())) {
            errors.add(message.getMessage("required.field", "cronExpression"));
        }

        if(triggerDTO.getCronExpression() != null && !CronExpression.isValidExpression(triggerDTO.getCronExpression())){
            errors.add(message.getMessage("trigger.cronexpression.invalid", triggerDTO.getCronExpression()));
        }
    }
}
