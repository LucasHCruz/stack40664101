package com.cnova.mpschedule.test.util;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.cnova.mpschedule.Application;
import com.cnova.mpschedule.ws.util.helper.FixtureHelper;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class IntegrationTestCommon {

    @BeforeClass
    public static void init() {
        FixtureFactoryLoader.loadTemplates(FixtureHelper.TEMPLATE_BASE_PACKAGE);
    }
}

