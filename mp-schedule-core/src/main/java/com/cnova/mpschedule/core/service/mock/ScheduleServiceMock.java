package com.cnova.mpschedule.core.service.mock;

import com.cnova.mpschedule.core.dto.ScheduleDTO;
import com.cnova.mpschedule.core.dto.TriggerDTO;
import com.cnova.mpschedule.core.service.ScheduleService;
import com.cnova.mpschedule.core.util.helper.FF4JHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(FF4JHelper.SCHEDULE_SERVICE_ALTERNATIVE)
public class ScheduleServiceMock implements ScheduleService {

    @Override
    public void schedule(ScheduleDTO schedule) {

    }

    @Override
    public List<ScheduleDTO> findSchedules() {
        return null;
    }

    @Override
    public void unscheduleJob(TriggerDTO trigger) {

    }
}
