package com.cnova.mpschedule.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"trigger", "job"})
@ApiModel(value = "schedule", description = "Agendamento para um Job")
public class ScheduleDTO {

    @ApiModelProperty(value = "Trigger do agendamento", required = true)
    TriggerDTO trigger;
    @ApiModelProperty(value = "Job a ser agendado", required = true)
    JobDetailDTO job;

    public ScheduleDTO(CronTrigger trigger, JobDetail jobDetail) {
        this.trigger = new TriggerDTO(trigger);
        this.job = new JobDetailDTO(jobDetail);
    }
}
