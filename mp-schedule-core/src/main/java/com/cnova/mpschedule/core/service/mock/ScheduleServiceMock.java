package com.cnova.mpschedule.core.service.mock;

import org.springframework.stereotype.Service;

import com.cnova.mpschedule.core.service.ScheduleService;
import com.cnova.mpschedule.core.util.helper.FF4JHelper;

@Service(FF4JHelper.SCHEDULE_SERVICE_ALTERNATIVE)
public class ScheduleServiceMock implements ScheduleService {

    @Override
    public String teste() {
        return "TESTE MOCK";
    }

}
