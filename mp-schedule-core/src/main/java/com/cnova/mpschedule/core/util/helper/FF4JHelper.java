package com.cnova.mpschedule.core.util.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FF4JHelper {
    
    //FF4J Geral Configuration
    public static final String FF4J_DATABASE_NAME = "mp-schedule";
    public static final String FF4J_COLLECTION = "ff4j";

    //FF4J Feature Configuration
    //Default implementation
    public static final String JOB_SERVICE = "job-service";
    public static final String SCHEDULE_SERVICE = "schedule-service";

    //Alternative implementation
    public static final String JOB_ALTERNATIVE = "job-service-alternative";
    public static final String SCHEDULE_SERVICE_ALTERNATIVE = "schedule-service-alternative";

}
