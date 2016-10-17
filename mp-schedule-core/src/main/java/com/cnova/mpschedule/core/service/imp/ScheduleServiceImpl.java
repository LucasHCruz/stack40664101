package com.cnova.mpschedule.core.service.imp;

import org.springframework.stereotype.Service;

import com.cnova.mpschedule.core.service.ScheduleService;
import com.cnova.mpschedule.core.util.helper.FF4JHelper;


@Service(FF4JHelper.SCHEDULE_SERVICE)
public class ScheduleServiceImpl implements ScheduleService {

    @Override
    public String teste() {
        return "TESTE";
    }

}
