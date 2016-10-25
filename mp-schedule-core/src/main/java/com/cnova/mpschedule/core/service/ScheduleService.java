package com.cnova.mpschedule.core.service;

import org.ff4j.aop.Flip;

import com.cnova.mpschedule.core.util.helper.FF4JHelper;

@Flip(name = FF4JHelper.SCHEDULE_SERVICE, alterBean = FF4JHelper.SCHEDULE_SERVICE_ALTERNATIVE)
public interface ScheduleService {

    String teste();

}
