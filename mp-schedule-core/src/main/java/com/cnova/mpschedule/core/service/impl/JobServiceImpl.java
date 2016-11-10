package com.cnova.mpschedule.core.service.impl;

import com.cnova.mpschedule.core.dto.JobDetailDTO;
import com.cnova.mpschedule.core.dto.validator.JobDetailDTOValidator;
import com.cnova.mpschedule.core.exception.MpScheduleException;
import com.cnova.mpschedule.core.job.BaseJob;
import com.cnova.mpschedule.core.service.JobService;
import com.cnova.mpschedule.core.util.Message;
import com.cnova.mpschedule.core.util.helper.FF4JHelper;
import com.cnova.mpschedule.core.util.helper.JobDataHelper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.impl.matchers.GroupMatcher.groupEquals;

@Slf4j
@Service(FF4JHelper.JOB_SERVICE)
public class JobServiceImpl implements JobService {
    @Autowired
    Message message;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    JobDetailDTOValidator jobDetailDTOValidator;

    @Override
    public List<JobDetailDTO> findJobs() {
        List<JobDetailDTO> jobsDTO = new ArrayList<>();

        try {
            for (String group : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(groupEquals(group))) {
                    JobDetail jobDetail = scheduler.getJobDetail(jobKey);

                    JobDetailDTO jobDetailDTO = new JobDetailDTO(jobDetail);
                    jobsDTO.add(jobDetailDTO);
                }
            }
        } catch (SchedulerException e) {
            String erro = message.getMessage("job.list.fail");
            log.warn(erro, e);
            throw new MpScheduleException(erro);
        }

        return jobsDTO;
    }

    @Override
    public void registerJob(JobDetailDTO job) {
        try {
            List<String> errors = jobDetailDTOValidator.validateRegisterJob(job);
            if (!errors.isEmpty()) {
                throw new MpScheduleException(message.getMessage("job.register.fail"), errors);
            }
            JobDetail jobDetail = createJobDetail(job);
            this.scheduler.addJob(jobDetail, false);
        } catch (SchedulerException e) {
            String erro = message.getMessage("job.register.fail", job.getKey().toString());
            log.warn(erro, e);
            throw new MpScheduleException(erro);
        }
    }

    @Override
    public void updateJob(JobDetailDTO job) {
        try {
            List<String> errors = jobDetailDTOValidator.validateUpdateJob(job);
            if (!errors.isEmpty()) {
                throw new MpScheduleException(message.getMessage("job.update.fail"), errors);
            }
            JobDetail jobDetail = createJobDetail(job);
            if (!this.scheduler.checkExists(job.getKey())) {
                String erro = message.getMessage("job.not.exists", job.getKey().toString());
                throw new MpScheduleException(erro);
            }
            this.scheduler.addJob(jobDetail, true);
        } catch (SchedulerException e) {
            String erro = message.getMessage("job.update.fail", job.getKey().toString());
            log.warn(erro, e);
            throw new MpScheduleException(erro);
        }
    }

    @Override
    public void deleteJob(JobDetailDTO job) {
        try {
            List<String> errors = jobDetailDTOValidator.validateDeleteUpdateJob(job);
            if (!errors.isEmpty()) {
                throw new MpScheduleException(message.getMessage("job.delete.fail"), errors);
            }
            this.scheduler.deleteJob(job.getKey());
        } catch (SchedulerException e) {
            String erro = message.getMessage("job.delete.fail", job.getKey().toString());
            log.warn(erro, e);
            throw new MpScheduleException(erro);
        }
    }

    private JobDetail createJobDetail(JobDetailDTO job) {
        return newJob(BaseJob.class)
                .withIdentity(job.getKey())
                .usingJobData(JobDataHelper.URL_DATA, job.getUrl())
                .storeDurably(true)
                .build();
    }
}
