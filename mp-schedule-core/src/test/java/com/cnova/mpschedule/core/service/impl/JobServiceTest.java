package com.cnova.mpschedule.core.service.impl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.cnova.mpschedule.core.dto.JobDetailDTO;
import com.cnova.mpschedule.core.dto.template.JobDetailTemplate;
import com.cnova.mpschedule.core.dto.validator.JobDetailDTOValidator;
import com.cnova.mpschedule.core.exception.MpScheduleException;
import com.cnova.mpschedule.core.job.BaseJob;
import com.cnova.mpschedule.core.util.Message;
import com.cnova.mpschedule.core.util.helper.FixtureHelper;
import com.cnova.mpschedule.core.util.helper.JobDataHelper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import java.util.Arrays;
import java.util.List;

import static com.cnova.mpschedule.core.helper.SchedulerTestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.impl.matchers.GroupMatcher.groupEquals;

@RunWith(MockitoJUnitRunner.class)
public class JobServiceTest {

    public static final String URL_TESTE = "www.teste.com.br/execute";
    public static final String URL_MOCK = "www.mock.com.br/run";
    public static final String REQUIRED_FIELD = "required.field";
    public static final String JOB_REGISTER_FAIL = "job.register.fail";
    private static final String JOB_UPDATE_FAIL = "job.update.fail";
    private static final String JOB_DELETE_FAIL = "job.delete.fail";
    @Mock
    Message message;

    @Mock
    Scheduler scheduler;

    @Spy
    @InjectMocks
    JobDetailDTOValidator jobDetailDTOValidator = new JobDetailDTOValidator();

    @InjectMocks
    JobServiceImpl jobService;

    @BeforeClass
    public static void init() {
        FixtureFactoryLoader.loadTemplates(FixtureHelper.TEMPLATE_BASE_PACKAGES);
    }

    @Before
    public void setUp() throws SchedulerException {
        SET_JOBKEY_GROUP_ONE_JOB_ONE.add(JOBKEY_GROUP_ONE_JOB_ONE);
        SET_JOBKEY_GROUP_TWO_JOB_TWO.add(JOBKEY_GROUP_TWO_JOB_TWO);

        JOB_ONE_GROUP_ONE = newJob(BaseJob.class)
                .withIdentity(JOB_ONE, GROUP_ONE)
                .storeDurably(true)
                .usingJobData(JobDataHelper.URL_DATA, URL_TESTE)
                .build();

        JOB_TWO_GROUP_TWO = newJob(BaseJob.class)
                .withIdentity(JOB_TWO, GROUP_TWO)
                .storeDurably(true)
                .usingJobData(JobDataHelper.URL_DATA, URL_MOCK)
                .build();


        when(scheduler.getJobGroupNames()).thenReturn(Arrays.asList(GROUP_ONE_JOB_ONE, GROUP_TWO_JOB_TWO));

        when(scheduler.getJobKeys(groupEquals(GROUP_ONE_JOB_ONE))).thenReturn(SET_JOBKEY_GROUP_ONE_JOB_ONE);
        when(scheduler.getJobKeys(groupEquals(GROUP_TWO_JOB_TWO))).thenReturn(SET_JOBKEY_GROUP_TWO_JOB_TWO);

        when(scheduler.getJobDetail(JOBKEY_GROUP_ONE_JOB_ONE)).thenReturn(JOB_ONE_GROUP_ONE);
        when(scheduler.getJobDetail(JOBKEY_GROUP_TWO_JOB_TWO)).thenReturn(JOB_TWO_GROUP_TWO);

        when(message.getMessage(eq(REQUIRED_FIELD), any(Object.class))).thenReturn(REQUIRED_FIELD);
        when(message.getMessage(eq(JOB_REGISTER_FAIL))).thenReturn(JOB_REGISTER_FAIL);
        when(message.getMessage(eq(JOB_UPDATE_FAIL))).thenReturn(JOB_UPDATE_FAIL);
        when(message.getMessage(eq(JOB_DELETE_FAIL))).thenReturn(JOB_DELETE_FAIL);
    }

    @Test
    public void listJobs_shouldReturnJobList(){
        JobDetailDTO jobOne = new JobDetailDTO(JOB_ONE_GROUP_ONE);
        JobDetailDTO jobTwo = new JobDetailDTO(JOB_TWO_GROUP_TWO);

        List<JobDetailDTO> jobsDetailDTO = jobService.findJobs();

        assertThat(jobsDetailDTO, hasItems(jobOne, jobTwo));
    }

    @Test(expected = MpScheduleException.class)
    public void registerJobWithoutValues_shouldReturnMpScheduleExceptionWithThreeRequiredFieldMessage(){
        JobDetailDTO jobWithoutJobName = Fixture.from(JobDetailDTO.class).gimme(JobDetailTemplate.WITHOUT_VALUES);

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
    public void updateJobWithoutValues_shouldReturnMpScheduleExceptionWithThreeRequiredFieldMessage(){
        JobDetailDTO jobWithoutValues = Fixture.from(JobDetailDTO.class).gimme(JobDetailTemplate.WITHOUT_VALUES);

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
    public void deleteJobWithoutValues_shouldReturnMpScheduleExceptionWithTwoRequiredFieldMessage(){
        JobDetailDTO jobWithoutValues = Fixture.from(JobDetailDTO.class).gimme(JobDetailTemplate.WITHOUT_VALUES);

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