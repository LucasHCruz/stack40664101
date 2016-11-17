package com.cnova.mpschedule.core.service.impl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.cnova.mpschedule.core.dto.JobDetailDTO;
import com.cnova.mpschedule.core.dto.template.JobDetailDTOTemplate;
import com.cnova.mpschedule.core.exception.MpScheduleException;
import com.cnova.mpschedule.core.util.helper.FixtureHelper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.quartz.JobKey;
import org.quartz.SchedulerException;

import java.util.List;

import static com.cnova.mpschedule.core.helper.SchedulerTestHelper.JOB_ONE_GROUP_ONE;
import static com.cnova.mpschedule.core.helper.SchedulerTestHelper.JOB_TWO_GROUP_TWO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobServiceTest extends QuartzSchedulerTest {

    public static final String JOB_REGISTER_FAIL = "job.register.fail";
    public static final String JOB_UPDATE_FAIL = "job.update.fail";
    public static final String JOB_DELETE_FAIL = "job.delete.fail";
    public static final String URL_MALFORMED = "job.url.malformed";

    @InjectMocks
    JobServiceImpl jobService;

    @BeforeClass
    public static void init() {
        FixtureFactoryLoader.loadTemplates(FixtureHelper.TEMPLATE_BASE_PACKAGES);
    }

    @Before
    public void setUp() throws SchedulerException {
        super.configureScheduler();

        when(message.getMessage(eq(JOB_REGISTER_FAIL), any(Object.class))).thenReturn(JOB_REGISTER_FAIL);
        when(message.getMessage(eq(JOB_UPDATE_FAIL), any(Object.class))).thenReturn(JOB_UPDATE_FAIL);
        when(message.getMessage(eq(JOB_DELETE_FAIL), any(Object.class))).thenReturn(JOB_DELETE_FAIL);
        when(message.getMessage(eq(URL_MALFORMED), any(Object.class))).thenReturn(URL_MALFORMED);
    }

    @Test
    public void listJobs_shouldReturnJobList() {
        JobDetailDTO jobOne = new JobDetailDTO(JOB_ONE_GROUP_ONE);
        JobDetailDTO jobTwo = new JobDetailDTO(JOB_TWO_GROUP_TWO);

        List<JobDetailDTO> jobsDetailDTO = jobService.findJobs();

        assertThat(jobsDetailDTO, containsInAnyOrder(jobOne, jobTwo));
    }

    @Test(expected = MpScheduleException.class)
    public void registerJobWithoutValues_shouldThrowMpScheduleExceptionWithThreeRequiredFieldMessage() {
        JobDetailDTO jobWithoutJobName = Fixture.from(JobDetailDTO.class).gimme(JobDetailDTOTemplate.WITHOUT_VALUES);

        try {
            jobService.registerJob(jobWithoutJobName);
        } catch (MpScheduleException e) {
            assertEquals(JOB_REGISTER_FAIL, e.getMessage());
            assertThat(e.getReasons(), contains(REQUIRED_FIELD, REQUIRED_FIELD, REQUIRED_FIELD));
            assertThat(e.getReasons(), hasSize(3));
            throw e;
        }
    }

    @Test(expected = MpScheduleException.class)
    public void updateJobWithoutValues_shouldThrowMpScheduleExceptionWithThreeRequiredFieldMessage() {
        JobDetailDTO jobWithoutValues = Fixture.from(JobDetailDTO.class).gimme(JobDetailDTOTemplate.WITHOUT_VALUES);

        try {
            jobService.updateJob(jobWithoutValues);
        } catch (MpScheduleException e) {
            assertEquals(JOB_UPDATE_FAIL, e.getMessage());
            assertThat(e.getReasons(), contains(REQUIRED_FIELD, REQUIRED_FIELD, REQUIRED_FIELD));
            assertThat(e.getReasons(), hasSize(3));
            throw e;
        }
    }

    @Test(expected = MpScheduleException.class)
    public void registerJobWithMalformerUrl_shouldThrowMpScheduleExceptionWithMalformedUrlException() throws SchedulerException {
        when(scheduler.checkExists(any(JobKey.class))).thenReturn(false);

        JobDetailDTO jobWithMalformedUrl = Fixture.from(JobDetailDTO.class).gimme(JobDetailDTOTemplate.MALFORMED_URL);
        try {
            jobService.registerJob(jobWithMalformedUrl);
        } catch (MpScheduleException e) {
            assertEquals(JOB_REGISTER_FAIL, e.getMessage());
            assertThat(e.getReasons(), contains(URL_MALFORMED));
            assertThat(e.getReasons(), hasSize(1));
            throw e;
        }
    }

    @Test(expected = MpScheduleException.class)
    public void deleteJobWithoutValues_shouldThrowMpScheduleExceptionWithTwoRequiredFieldMessage() {
        JobDetailDTO jobWithoutValues = Fixture.from(JobDetailDTO.class).gimme(JobDetailDTOTemplate.WITHOUT_VALUES);

        try {
            jobService.deleteJob(jobWithoutValues);
        } catch (MpScheduleException e) {
            assertEquals(JOB_DELETE_FAIL, e.getMessage());
            assertThat(e.getReasons(), contains(REQUIRED_FIELD, REQUIRED_FIELD));
            assertThat(e.getReasons(), hasSize(2));
            throw e;
        }
    }
}