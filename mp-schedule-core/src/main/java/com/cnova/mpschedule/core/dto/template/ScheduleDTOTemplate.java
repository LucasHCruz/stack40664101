package com.cnova.mpschedule.core.dto.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.cnova.mpschedule.core.dto.JobDetailDTO;
import com.cnova.mpschedule.core.dto.ScheduleDTO;
import com.cnova.mpschedule.core.dto.TriggerDTO;

public class  ScheduleDTOTemplate implements TemplateLoader {
    public static final String VALID = "VALID";
    public static final String WITHOUT_VALUES = "WITHOUT_VALUES";
    public static final String WITHOUT_TRIGGER_AND_JOB_VALUES = "WITHOUT_TRIGGER_AND_JOB_VALUES";
    public static final String INVALID_CRON_EXPRESSION = "INVALID_CRON_EXPRESSION";

    @Override
    public void load() {
        Fixture.of(ScheduleDTO.class).addTemplate(VALID, new Rule() {{
            add("trigger", one(TriggerDTO.class, TriggerDTOTemplate.VALID));
            add("job", one(JobDetailDTO.class, JobDetailDTOTemplate.VALID));
        }});

        Fixture.of(ScheduleDTO.class).addTemplate(WITHOUT_VALUES, new Rule() {{
            add("trigger", null);
            add("job", null);
        }});

        Fixture.of(ScheduleDTO.class).addTemplate(WITHOUT_TRIGGER_AND_JOB_VALUES, new Rule() {{
            add("trigger", one(TriggerDTO.class, TriggerDTOTemplate.WITHOUT_VALUES));
            add("job", one(JobDetailDTO.class, JobDetailDTOTemplate.WITHOUT_VALUES));
        }});

        Fixture.of(ScheduleDTO.class).addTemplate(WITHOUT_TRIGGER_AND_JOB_VALUES, new Rule() {{
            add("trigger", one(TriggerDTO.class, TriggerDTOTemplate.WITHOUT_VALUES));
            add("job", one(JobDetailDTO.class, JobDetailDTOTemplate.WITHOUT_VALUES));
        }});

        Fixture.of(ScheduleDTO.class).addTemplate(INVALID_CRON_EXPRESSION, new Rule() {{
            add("trigger", one(TriggerDTO.class, TriggerDTOTemplate.INVALID_CRON_EXPRESSION));
            add("job", one(JobDetailDTO.class, JobDetailDTOTemplate.VALID));
        }});
    }
}
