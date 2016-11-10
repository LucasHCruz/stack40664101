package com.cnova.mpschedule.core.helper;

import com.cnova.mpschedule.core.dto.ScheduleDTO;
import com.cnova.mpschedule.core.job.BaseJob;
import com.cnova.mpschedule.core.util.helper.JobDataHelper;
import org.quartz.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class SchedulerTestHelper {

    private static final String PERIOD = ".";

    public static final String URL_TESTE = "www.teste.com.br/execute";
    public static final String URL_MOCK = "www.mock.com.br/run";
    public static final String CRON_UM_MINUTO = "0 0/1 * * * ?";
    public static final String CRON_UMA_HORA = "0 0 0/1 * * ?";

    public static final String STRING_GROUP_ONE = "group-one";
    public static final String STRING_GROUP_TWO = "group-two";
    public static final String STRING_JOB_ONE = "job-one";
    public static final String STRING_JOB_TWO = "job-two";
    public static final String STRING_TRIGGER_ONE = "trigger-one";
    public static final String STRING_TRIGGER_TWO = "trigger-two";

    public static final String STRING_GROUP_ONE_JOB_ONE = STRING_GROUP_ONE + PERIOD + STRING_JOB_ONE;
    public static final String STRING_GROUP_TWO_JOB_TWO = STRING_GROUP_TWO + PERIOD + STRING_JOB_TWO;
    public static final String STRING_GROUP_ONE_TRIGGER_ONE = STRING_GROUP_ONE + PERIOD + STRING_TRIGGER_ONE;
    public static final String STRING_GROUP_TWO_TRIGGER_TWO = STRING_GROUP_TWO + PERIOD + STRING_TRIGGER_TWO;

    public static final JobKey JOBKEY_GROUP_ONE_JOB_ONE = new JobKey(STRING_JOB_ONE, STRING_GROUP_ONE);
    public static final JobKey JOBKEY_GROUP_TWO_JOB_TWO = new JobKey(STRING_JOB_TWO, STRING_GROUP_TWO);

    public static final TriggerKey TRIGGERKEY_GROUP_ONE_TRIGGER_ONE = new TriggerKey(STRING_TRIGGER_ONE, STRING_GROUP_ONE);
    public static final TriggerKey TRIGGERKEY_GROUP_TWO_TRIGGER_TWO = new TriggerKey(STRING_TRIGGER_TWO, STRING_TRIGGER_TWO);

    public static final Set<JobKey> SET_JOBKEY_GROUP_ONE_JOB_ONE = new HashSet<>(Arrays.asList(JOBKEY_GROUP_ONE_JOB_ONE));
    public static final Set<JobKey> SET_JOBKEY_GROUP_TWO_JOB_TWO = new HashSet<>(Arrays.asList(JOBKEY_GROUP_TWO_JOB_TWO));

    public static final Set<TriggerKey> SET_TRIGGERKEY_GROUP_ONE_JOB_ONE = new HashSet<>(Arrays.asList(TRIGGERKEY_GROUP_ONE_TRIGGER_ONE));
    public static final Set<TriggerKey> SET_TRIGGERKEY_GROUP_TWO_JOB_TWO = new HashSet<>(Arrays.asList(TRIGGERKEY_GROUP_TWO_TRIGGER_TWO));

    public static JobDetail JOB_ONE_GROUP_ONE = newJob(BaseJob.class)
                .withIdentity(STRING_JOB_ONE, STRING_GROUP_ONE)
                .storeDurably(true)
                .usingJobData(JobDataHelper.URL_DATA, URL_TESTE)
                .build();

    public static JobDetail JOB_TWO_GROUP_TWO = newJob(BaseJob.class)
                .withIdentity(STRING_JOB_TWO, STRING_GROUP_TWO)
                .storeDurably(true)
                .usingJobData(JobDataHelper.URL_DATA, URL_MOCK)
                .build();

    public static CronTrigger TRIGGER_ONE_GROUP_ONE = newTrigger()
            .withIdentity(STRING_TRIGGER_ONE, STRING_GROUP_ONE)
            .withSchedule(
                    CronScheduleBuilder.cronSchedule(CRON_UM_MINUTO)
            )
            .forJob(JOBKEY_GROUP_ONE_JOB_ONE)
            .build();

    public static CronTrigger TRIGGER_TWO_GROUP_TWO = newTrigger()
            .withIdentity(STRING_TRIGGER_TWO, STRING_GROUP_TWO)
            .withSchedule(
                    CronScheduleBuilder.cronSchedule(CRON_UMA_HORA)
            )
            .forJob(JOBKEY_GROUP_TWO_JOB_TWO)
            .build();

    public static final ScheduleDTO SCHEDULE_JOB_ONE_TRIGGER_ONE = new ScheduleDTO(TRIGGER_ONE_GROUP_ONE, JOB_ONE_GROUP_ONE);
    public static final ScheduleDTO SCHEDULE_JOB_TWO_TRIGGER_TWO = new ScheduleDTO(TRIGGER_TWO_GROUP_TWO, JOB_TWO_GROUP_TWO);
}
