package com.cnova.mpschedule.core.helper;

import org.quartz.JobDetail;
import org.quartz.JobKey;

import java.util.HashSet;
import java.util.Set;

public class SchedulerTestHelper {

    private static final String PERIOD = ".";

    public static final String GROUP_ONE = "group-one";
    public static final String GROUP_TWO = "group-two";
    public static final String JOB_ONE = "job-one";
    public static final String JOB_TWO = "job-two";
    public static final String TRIGGER_ONE = "trigger-one";
    public static final String TRIGGER_TWO = "trigger-two";

    public static final String GROUP_ONE_JOB_ONE = GROUP_ONE + PERIOD + JOB_ONE;
    public static final String GROUP_TWO_JOB_TWO = GROUP_TWO + PERIOD + JOB_TWO;
    public static final String GROUP_ONE_TRIGGER_ONE = GROUP_ONE + PERIOD + TRIGGER_ONE;
    public static final String GROUP_TWO_TRIGGER_TWO = GROUP_TWO + PERIOD + TRIGGER_TWO;

    public static final JobKey JOBKEY_GROUP_ONE_JOB_ONE = new JobKey(GROUP_ONE_JOB_ONE);
    public static final JobKey JOBKEY_GROUP_TWO_JOB_TWO = new JobKey(GROUP_TWO_JOB_TWO);

    public static final Set<JobKey> SET_JOBKEY_GROUP_ONE_JOB_ONE = new HashSet<>();
    public static final Set<JobKey> SET_JOBKEY_GROUP_TWO_JOB_TWO = new HashSet<>();

    public static JobDetail JOB_ONE_GROUP_ONE;
    public static JobDetail JOB_TWO_GROUP_TWO;
}
