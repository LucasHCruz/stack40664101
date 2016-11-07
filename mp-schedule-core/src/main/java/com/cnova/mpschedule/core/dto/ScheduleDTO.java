package com.cnova.mpschedule.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleDTO {

    TriggerDTO trigger;
    JobDetailDTO jobDetail;
}
