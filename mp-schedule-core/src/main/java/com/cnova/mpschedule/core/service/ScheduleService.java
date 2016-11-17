package com.cnova.mpschedule.core.service;

import com.cnova.mpschedule.core.dto.ScheduleDTO;
import com.cnova.mpschedule.core.dto.TriggerDTO;
import com.cnova.mpschedule.core.util.helper.FF4JHelper;
import org.ff4j.aop.Flip;

import java.util.List;

@Flip(name = FF4JHelper.SCHEDULE_SERVICE, alterBean = FF4JHelper.SCHEDULE_SERVICE_ALTERNATIVE)
public interface ScheduleService {

    void schedule(ScheduleDTO schedule);

    List<ScheduleDTO> findSchedules();

    void unscheduleJob(TriggerDTO trigger);
}
