package com.cnova.mpschedule.core.dto.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.cnova.mpschedule.core.dto.JobDetailDTO;
import com.cnova.mpschedule.core.util.helper.FixtureHelper;

public class JobDetailDTOTemplate implements TemplateLoader{
    public static final String VALID = "VALID";
    public static final String WITHOUT_JOB_NAME = "WITHOUT_JOB_NAME";
    public static final String WITHOUT_GROUP_NAME_AND_JOB_NAME = "WITHOUT_GROUP_NAME_AND_JOB_NAME";
    public static final String WITHOUT_VALUES = "WITHOUT_VALUES";

    @Override
    public void load() {
        Fixture.of(JobDetailDTO.class).addTemplate(VALID, new Rule(){{
            add("jobName", "job-name");
            add("groupName", "group-name");
            add("url", regex(FixtureHelper.REGEX_URL));
        }});

        Fixture.of(JobDetailDTO.class).addTemplate(WITHOUT_JOB_NAME).inherits(VALID, new Rule(){{
            add("jobName", null);
        }});

        Fixture.of(JobDetailDTO.class).addTemplate(WITHOUT_GROUP_NAME_AND_JOB_NAME).inherits(VALID, new Rule(){{
            add("groupName", null);
            add("jobName", null);
        }});

        Fixture.of(JobDetailDTO.class).addTemplate(WITHOUT_VALUES).inherits(VALID, new Rule(){{
            add("groupName", null);
            add("jobName", null);
            add("url", null);
        }});
    }
}
