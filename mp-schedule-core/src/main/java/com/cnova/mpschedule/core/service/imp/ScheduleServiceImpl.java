package com.cnova.mpschedule.core.service.imp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.cnova.mpschedule.core.job.MyJobTwo;
import com.cnova.mpschedule.core.service.ScheduleService;
import com.cnova.mpschedule.core.util.helper.FF4JHelper;


@Service(FF4JHelper.SCHEDULE_SERVICE)
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private SchedulerFactoryBean scheduler;
    
    
    @Override
    public String teste() {
        
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(MyJobTwo.class);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "RAM");
        map.put(MyJobTwo.COUNT, 1);
        factory.setJobDataAsMap(map);
        factory.setGroup("mygroup");
        factory.setName("myjob");
        
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        stFactory.setJobDetail(factory.getObject());
        stFactory.setStartDelay(3000);
        stFactory.setName("mytrigger");
        stFactory.setGroup("mygroup");
        stFactory.setCronExpression("0 0/1 * * * ?");
        
        scheduler.setTriggers(stFactory.getObject());
        
        scheduler.start();
        
        return "TESTE OK";
    }

}
