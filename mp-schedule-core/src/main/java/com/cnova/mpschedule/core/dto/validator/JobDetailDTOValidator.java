package com.cnova.mpschedule.core.dto.validator;

import com.cnova.mpschedule.core.dto.JobDetailDTO;
import com.cnova.mpschedule.core.util.Message;
import com.google.common.base.Strings;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobDetailDTOValidator {

    @Autowired
    Message message;

    @Autowired
    Scheduler scheduler;

    public List<String> validateRegisterJob(JobDetailDTO jobDetailDTO) throws SchedulerException {
        List<String> errors = new ArrayList<>();

        verifyJobKey(jobDetailDTO, errors);
        verifyUrl(jobDetailDTO, errors);
        verifyIfJobAlreadyExists(jobDetailDTO, errors);

        return errors;
    }

    public List<String> validateUpdateJob(JobDetailDTO jobDetailDTO) throws SchedulerException {
        List<String> errors = new ArrayList<>();

        verifyJobKey(jobDetailDTO, errors);
        verifyUrl(jobDetailDTO, errors);
        verifyIfJobNotExists(jobDetailDTO, errors);

        return errors;
    }

    public List<String> validateDeleteUpdateJob(JobDetailDTO jobDetailDTO) throws SchedulerException {
        List<String> errors = new ArrayList<>();

        verifyJobKey(jobDetailDTO, errors);
        verifyIfJobNotExists(jobDetailDTO, errors);

        return errors;
    }

    protected void verifyJobKey(JobDetailDTO jobDetailDTO, List<String> errors) {
        if(Strings.isNullOrEmpty(jobDetailDTO.getJobName())){
            errors.add(message.getMessage("required.field", "jobName"));
        }

        if(Strings.isNullOrEmpty(jobDetailDTO.getGroupName())){
            errors.add(message.getMessage("required.field", "groupName"));
        }
    }

    protected void verifyUrl(JobDetailDTO jobDetailDTO, List<String> errors) {
        if(Strings.isNullOrEmpty(jobDetailDTO.getUrl())){
            errors.add(message.getMessage("required.field", "url"));
            return;
        }

        /*if(!UrlValidator.getInstance().isValid(jobDetailDTO.getUrl())){
            errors.add(message.getMessage("job.url.malformed", jobDetailDTO.getUrl()));
        }*/
    }

    protected void verifyIfJobAlreadyExists(JobDetailDTO jobDetailDTO, List<String> errors) throws SchedulerException {
        if(Strings.isNullOrEmpty(jobDetailDTO.getGroupName()) || Strings.isNullOrEmpty(jobDetailDTO.getJobName())){
            //não adiciona nenhum erro, porque o verifyJobKey() já adiciona os erros na lista.
            return;
        }
        if(scheduler.checkExists(jobDetailDTO.getKey())){
            errors.add(message.getMessage("job.already.exists", jobDetailDTO.getKey().toString()));
        }
    }

    protected void verifyIfJobNotExists(JobDetailDTO jobDetailDTO, List<String> errors) throws SchedulerException {
        if(Strings.isNullOrEmpty(jobDetailDTO.getGroupName()) || Strings.isNullOrEmpty(jobDetailDTO.getJobName())){
            //não adiciona nenhum erro, porque o verifyJobKey() já adiciona os erros na lista.
            return;
        }
        if(scheduler.checkExists(jobDetailDTO.getKey())){
            errors.add(message.getMessage("job.not.exists", jobDetailDTO.getKey().toString()));
        }
    }
}
