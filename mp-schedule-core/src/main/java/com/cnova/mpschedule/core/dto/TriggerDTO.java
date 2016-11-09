package com.cnova.mpschedule.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.quartz.CronTrigger;
import org.quartz.TriggerKey;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"triggerName", "groupName"})
@ApiModel(value = "trigger", description = "Trigger para agendar jobs")
public class TriggerDTO {

    @ApiModelProperty(value = "Nome da trigger", example = "trigger-name", required = true)
    private String triggerName;
    @ApiModelProperty(value = "Grupo da trigger", example = "group-name", required = true)
    private String groupName;
    @ApiModelProperty(value = "Expressão cronológica (CronExpression)", example = "0 0/5 * * * ?", required = true)
    private String cronExpression;

    @JsonIgnore
    public TriggerKey getKey() {
        return new TriggerKey(this.getTriggerName(), this.getGroupName());
    }

    public TriggerDTO(CronTrigger trigger) {
        this.triggerName = trigger.getKey().getName();
        this.groupName = trigger.getKey().getGroup();
        this.cronExpression = trigger.getCronExpression();
    }
}
