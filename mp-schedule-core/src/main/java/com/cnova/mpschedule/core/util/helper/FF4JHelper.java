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
    public static final String RIGHT_NOW_SERVICE = "right-now-service";
    public static final String TDCA_SERVICE = "tdca-service";

    //Alternative implementation
    public static final String RIGHT_NOW_SERVICE_ALTERNATIVE = "right-now-service-alternative";
    public static final String TDCA_SERVICE_ALTERNATIVE = "tdca-service-alternative";
    
}
