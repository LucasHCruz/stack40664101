package com.cnova.mpschedule.ws.dto.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.cnova.mpschedule.core.dto.ScheduleDTO;

public class ScheduleDTOTemplateLoader implements TemplateLoader{
    public static final String VALID = "VALID";

    @Override
    public void load() {
        Fixture.of(ScheduleDTO.class).addTemplate(VALID, new Rule(){{
            //add("cronExpression", regex(FixtureHelper.REGEX_CRON));
            //add("jobUrl", regex(FixtureHelper.REGEX_URL));
        }});
    }
}
