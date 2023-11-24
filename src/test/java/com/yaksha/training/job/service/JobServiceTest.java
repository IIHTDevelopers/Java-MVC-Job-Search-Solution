package com.yaksha.training.job.service;

import com.yaksha.training.job.entity.Job;
import com.yaksha.training.job.repository.JobRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.yaksha.training.job.utils.MasterData.asJsonString;
import static com.yaksha.training.job.utils.MasterData.getJob;
import static com.yaksha.training.job.utils.MasterData.getJobList;
import static com.yaksha.training.job.utils.TestUtils.businessTestFile;
import static com.yaksha.training.job.utils.TestUtils.currentTest;
import static com.yaksha.training.job.utils.TestUtils.testReport;
import static com.yaksha.training.job.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

public class JobServiceTest {

    @InjectMocks
    private JobService jobService;

    @Mock
    private JobRepository jobRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testServiceGetJobs() throws Exception {
        List<Job> actual = getJobList(5);
        when(jobRepository.findAll()).thenReturn(actual);
        List<Job> expected = jobService.getJobs();
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSaveJob() throws Exception {
        Job actual = getJob();
        when(jobRepository.save(actual)).thenReturn(actual);
        Job expected = jobService.saveJob(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceGetJob() throws Exception {
        Job actual = getJob();
        when(jobRepository.findById(actual.getId())).thenReturn(Optional.of(actual));
        Job expected = jobService.getJob(actual.getId());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }


    @Test
    public void testServiceDeleteJob() throws Exception {
        Job actual = getJob();
        boolean expected = jobService.deleteJob(actual);
        yakshaAssert(currentTest(),
                (expected ? true : false),
                businessTestFile);
    }

}
