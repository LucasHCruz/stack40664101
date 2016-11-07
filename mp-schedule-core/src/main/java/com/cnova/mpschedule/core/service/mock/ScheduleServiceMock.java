package com.cnova.mpschedule.core.service.mock;

import com.cnova.mpschedule.core.dto.JobDetailDTO;
import com.cnova.mpschedule.core.dto.ScheduleDTO;
import com.cnova.mpschedule.core.service.ScheduleService;
import com.cnova.mpschedule.core.util.helper.FF4JHelper;
import org.quartz.JobDetail;
import org.springframework.stereotype.Service;

@Service(FF4JHelper.SCHEDULE_SERVICE_ALTERNATIVE)
public class ScheduleServiceMock implements ScheduleService {

    @Override
    public JobDetail registerJob(JobDetailDTO job) {
        return null;
    }

    @Override
    public void schedule(ScheduleDTO schedule) {

    }
}
