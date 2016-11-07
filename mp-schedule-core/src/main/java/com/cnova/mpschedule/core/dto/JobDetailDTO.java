package com.cnova.mpschedule.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.quartz.JobKey;

@Data
@NoArgsConstructor
public class JobDetailDTO {

    private String jobName;
    private String groupName;
    private String url;

    public JobKey getKey(){
        return new JobKey(this.getJobName(), this.getGroupName());
    }
}
