package com.cnova.mpschedule.core.service;

import com.cnova.mpschedule.core.dto.JobDetailDTO;
import com.cnova.mpschedule.core.dto.ScheduleDTO;
import com.cnova.mpschedule.core.util.helper.FF4JHelper;
import org.ff4j.aop.Flip;
import org.quartz.JobDetail;

@Flip(name = FF4JHelper.SCHEDULE_SERVICE, alterBean = FF4JHelper.SCHEDULE_SERVICE_ALTERNATIVE)
public interface ScheduleService {

    JobDetail registerJob(JobDetailDTO job);

    void schedule(ScheduleDTO schedule);
}
