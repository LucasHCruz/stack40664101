package com.cnova.mpschedule.core.dto.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.cnova.mpschedule.core.dto.TriggerDTO;

public class TriggerDTOTemplate implements TemplateLoader {
    public static final String VALID = "VALID";
    public static final String WITHOUT_VALUES = "WITHOUT_VALUES";
    public static final String INVALID_CRON_EXPRESSION = "INVALID_CRON_EXPRESSION";

    @Override
    public void load() {
        Fixture.of(TriggerDTO.class).addTemplate(VALID, new Rule(){{
            add("triggerName", "trigger-name");
            add("groupName", "group-name");
            add("cronExpression", random("0 * * * * ?", "0 0/1 * * * ?", "0 0 0/1 * * ?"));
        }});

        Fixture.of(TriggerDTO.class).addTemplate(WITHOUT_VALUES, new Rule(){{
            add("triggerName", null);
            add("groupName", null);
            add("cronExpression", null);
        }});

        Fixture.of(TriggerDTO.class).addTemplate(INVALID_CRON_EXPRESSION).inherits(VALID, new Rule(){{
            add("cronExpression", "invalid cron");
        }});
    }
}
