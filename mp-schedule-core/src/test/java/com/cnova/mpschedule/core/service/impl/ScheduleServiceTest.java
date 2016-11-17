package com.cnova.mpschedule.core.service.impl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.cnova.mpschedule.core.dto.ScheduleDTO;
import com.cnova.mpschedule.core.dto.TriggerDTO;
import com.cnova.mpschedule.core.dto.template.ScheduleDTOTemplate;
import com.cnova.mpschedule.core.dto.template.TriggerDTOTemplate;
import com.cnova.mpschedule.core.exception.MpScheduleException;
import com.cnova.mpschedule.core.util.helper.FixtureHelper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;

import java.util.List;

import static com.cnova.mpschedule.core.helper.SchedulerTestHelper.SCHEDULE_JOB_ONE_TRIGGER_ONE;
import static com.cnova.mpschedule.core.helper.SchedulerTestHelper.SCHEDULE_JOB_TWO_TRIGGER_TWO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTest extends QuartzSchedulerTest {

    private static final String SCHEDULE_FAIL = "schedule.fail";
    private static final String INVALID_CRON_EXPRESSION = "trigger.cronexpression.invalid";
    private static final String UNSCHEDULE_FAIL = "schedule.unschedule.fail";


    @InjectMocks
    ScheduleServiceImpl scheduleService;

    @BeforeClass
    public static void init() {
        FixtureFactoryLoader.loadTemplates(FixtureHelper.TEMPLATE_BASE_PACKAGES);
    }

    @Before
    public void setUp() throws SchedulerException {
        super.configureScheduler();

        when(message.getMessage(eq(SCHEDULE_FAIL), any(Object.class))).thenReturn(SCHEDULE_FAIL);
        when(message.getMessage(eq(UNSCHEDULE_FAIL), any(Object.class))).thenReturn(UNSCHEDULE_FAIL);
        when(message.getMessage(eq(INVALID_CRON_EXPRESSION), any(Object.class))).thenReturn(INVALID_CRON_EXPRESSION);
    }

    @Test
    public void findSchedules() {
        List<ScheduleDTO> scheduleDTOs = scheduleService.findSchedules();

        assertThat(scheduleDTOs, containsInAnyOrder(SCHEDULE_JOB_ONE_TRIGGER_ONE, SCHEDULE_JOB_TWO_TRIGGER_TWO));
        assertThat(scheduleDTOs, hasSize(2));
    }

    @Test(expected = MpScheduleException.class)
    public void testScheduleWithoutValues_shouldThrowMpScheduleExceptionWithTwoRequiredFieldMessage() {
        ScheduleDTO scheduleWithoutValues = Fixture.from(ScheduleDTO.class).gimme(ScheduleDTOTemplate.WITHOUT_VALUES);

        try {
            scheduleService.schedule(scheduleWithoutValues);
        } catch (MpScheduleException e) {
            assertEquals(SCHEDULE_FAIL, e.getMessage());
            assertThat(e.getReasons(), contains(REQUIRED_FIELD, REQUIRED_FIELD));
            assertThat(e.getReasons(), hasSize(2));

            throw e;
        }
    }

    @Test(expected = MpScheduleException.class)
    public void testScheduleWithoutTriggerAndJobValues_shouldThrowMpScheduleExceptionWithFiveRequiredFieldMessage() {
        ScheduleDTO scheduleWithoutTriggerAndJobValues = Fixture.from(ScheduleDTO.class).gimme(ScheduleDTOTemplate.WITHOUT_TRIGGER_AND_JOB_VALUES);

        try {
            scheduleService.schedule(scheduleWithoutTriggerAndJobValues);
        } catch (MpScheduleException e) {
            assertEquals(SCHEDULE_FAIL, e.getMessage());
            assertThat(e.getReasons(), contains(REQUIRED_FIELD, REQUIRED_FIELD, REQUIRED_FIELD, REQUIRED_FIELD, REQUIRED_FIELD));
            assertThat(e.getReasons(), hasSize(5));

            throw e;
        }
    }

    @Test(expected = MpScheduleException.class)
    public void testScheduleWithInvalidCronExpression_shouldThrowMpScheduleExceptionWithInvalidCronExpressionMessage() throws SchedulerException {
        when(scheduler.checkExists(any(TriggerKey.class))).thenReturn(false);

        ScheduleDTO scheduleWithInvalidCronExpression = Fixture.from(ScheduleDTO.class).gimme(ScheduleDTOTemplate.INVALID_CRON_EXPRESSION);

        try {
            scheduleService.schedule(scheduleWithInvalidCronExpression);
        } catch (MpScheduleException e) {
            assertEquals(SCHEDULE_FAIL, e.getMessage());
            assertThat(e.getReasons(), contains(INVALID_CRON_EXPRESSION));
            assertThat(e.getReasons(), hasSize(1));

            throw e;
        }
    }

    @Test(expected = MpScheduleException.class)
    public void testUnscheduleJobWithTriggerWithoutValues_shouldThrowMpScheduleExceptionWithTwoRequiredFieldMessage(){
        TriggerDTO triggerWithoutValues = Fixture.from(TriggerDTO.class).gimme(TriggerDTOTemplate.WITHOUT_VALUES);

        try {
            scheduleService.unscheduleJob(triggerWithoutValues);
        } catch (MpScheduleException e) {
            assertEquals(UNSCHEDULE_FAIL, e.getMessage());
            assertThat(e.getReasons(), contains(REQUIRED_FIELD, REQUIRED_FIELD));
            assertThat(e.getReasons(), hasSize(2));

            throw e;
        }
    }
}
