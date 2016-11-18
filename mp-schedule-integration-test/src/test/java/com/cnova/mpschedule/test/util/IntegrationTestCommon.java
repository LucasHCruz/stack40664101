package com.cnova.mpschedule.test.util;


import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cnova.mpschedule.config.Application;
import com.cnova.mpschedule.core.util.helper.FixtureHelper;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class IntegrationTestCommon {

    @BeforeClass
    public static void init() {
        FixtureFactoryLoader.loadTemplates(FixtureHelper.TEMPLATE_BASE_PACKAGES);
    }
}
