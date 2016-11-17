package com.cnova.mpschedule.core.dto;

import com.cnova.mpschedule.core.util.helper.JobDataHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.quartz.JobDetail;
import org.quartz.JobKey;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"jobName", "groupName"})
@ApiModel(value = "job", description = "Job para ser executado por um agendamento")
public class JobDetailDTO {

    @ApiModelProperty(value = "Nome do job", example = "job-name", required = true)
    private String jobName;
    @ApiModelProperty(value = "Grupo do job", example = "group-name", required = true)
    private String groupName;
    @ApiModelProperty(value = "Url em que o verdadeiro job está", example = "www.chamameujob.com.br/", required = true)
    private String url;

    @JsonIgnore
    public JobKey getKey(){
        return new JobKey(this.getJobName(), this.getGroupName());
    }

    public JobDetailDTO(JobDetail jobDetail) {
        this.jobName = jobDetail.getKey().getName();
        this.groupName = jobDetail.getKey().getGroup();
        this.url = jobDetail.getJobDataMap().getString(JobDataHelper.URL_DATA);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("job")
                .add("jobName", jobName)
                .add("groupName", groupName)
                .toString();
    }
}
