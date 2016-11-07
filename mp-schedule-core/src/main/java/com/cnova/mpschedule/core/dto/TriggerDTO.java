package com.cnova.mpschedule.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TriggerDTO {

    private String triggerName;
    private String groupName;
    private String cronExpression;
}
