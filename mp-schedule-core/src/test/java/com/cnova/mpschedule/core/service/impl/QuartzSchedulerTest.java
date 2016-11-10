package com.cnova.mpschedule.core.service.impl;

import com.cnova.mpschedule.core.dto.validator.JobDetailDTOValidator;
import com.cnova.mpschedule.core.dto.validator.ScheduleDTOValidator;
import com.cnova.mpschedule.core.dto.validator.TriggerDTOValidator;
import com.cnova.mpschedule.core.util.Message;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;

import java.util.Arrays;

import static com.cnova.mpschedule.core.helper.SchedulerTestHelper.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.quartz.impl.matchers.GroupMatcher.groupEquals;

public class QuartzSchedulerTest {

    public static final String REQUIRED_FIELD = "required.field";

    @Mock
    Scheduler scheduler;

    @Mock
    Message message;

    @Spy
    @InjectMocks
    JobDetailDTOValidator jobDetailDTOValidator;

    @Spy
    @InjectMocks
    TriggerDTOValidator triggerDTOValidator;

    @Spy
    @InjectMocks
    ScheduleDTOValidator scheduleDTOValidator;

    public void configureScheduler() throws SchedulerException {
        when(scheduler.getJobGroupNames()).thenReturn(Arrays.asList(STRING_GROUP_ONE_JOB_ONE, STRING_GROUP_TWO_JOB_TWO));
        when(scheduler.getTriggerGroupNames()).thenReturn(Arrays.asList(STRING_GROUP_ONE_TRIGGER_ONE, STRING_GROUP_TWO_TRIGGER_TWO));

        when(scheduler.getJobKeys(groupEquals(STRING_GROUP_ONE_JOB_ONE))).thenReturn(SET_JOBKEY_GROUP_ONE_JOB_ONE);
        when(scheduler.getJobKeys(groupEquals(STRING_GROUP_TWO_JOB_TWO))).thenReturn(SET_JOBKEY_GROUP_TWO_JOB_TWO);

        when(scheduler.getTriggerKeys(groupEquals(STRING_GROUP_ONE_TRIGGER_ONE))).thenReturn(SET_TRIGGERKEY_GROUP_ONE_JOB_ONE);
        when(scheduler.getTriggerKeys(groupEquals(STRING_GROUP_TWO_TRIGGER_TWO))).thenReturn(SET_TRIGGERKEY_GROUP_TWO_JOB_TWO);

        when(scheduler.getJobDetail(JOBKEY_GROUP_ONE_JOB_ONE)).thenReturn(JOB_ONE_GROUP_ONE);
        when(scheduler.getJobDetail(JOBKEY_GROUP_TWO_JOB_TWO)).thenReturn(JOB_TWO_GROUP_TWO);

        when(scheduler.getTrigger(TRIGGERKEY_GROUP_ONE_TRIGGER_ONE)).thenReturn(TRIGGER_ONE_GROUP_ONE);
        when(scheduler.getTrigger(TRIGGERKEY_GROUP_TWO_TRIGGER_TWO)).thenReturn(TRIGGER_TWO_GROUP_TWO);

        when(scheduler.checkExists(any(JobKey.class))).thenReturn(true);
        when(scheduler.checkExists(any(TriggerKey.class))).thenReturn(true);

        when(message.getMessage(eq(REQUIRED_FIELD), any(Object.class))).thenReturn(REQUIRED_FIELD);
    }
}
