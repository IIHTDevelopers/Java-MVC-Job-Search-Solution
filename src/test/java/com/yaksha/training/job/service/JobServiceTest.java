package com.yaksha.training.job.service;

import com.yaksha.training.job.entity.Job;
import com.yaksha.training.job.repository.JobRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.yaksha.training.job.utils.MasterData.asJsonString;
import static com.yaksha.training.job.utils.MasterData.getJob;
import static com.yaksha.training.job.utils.MasterData.getJobList;
import static com.yaksha.training.job.utils.MasterData.randomBoolean;
import static com.yaksha.training.job.utils.MasterData.randomStringWithSize;
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

    @Test
    public void testServiceSearchJobsWithNullKeys() throws Exception {
        List<Job> jobs = getJobList(5);
        Pageable pageable = PageRequest.of(0, 5);
        List<Job> notFavJobs = jobs.stream().filter(job -> !job.isFav()).toList();
        Page<Job> expected = new PageImpl<>(notFavJobs);
        when(jobRepository.findByJobTitleAndJobDescription(null, pageable)).thenReturn(expected);
        Page<Job> actual = jobService.searchJobs(null, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual.getContent()))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSearchJobsWithKeys() throws Exception {
        String theSearchName = randomStringWithSize(5);
        List<Job> jobs = getJobList(5);
        Pageable pageable = PageRequest.of(0, 5);
        List<Job> notFavJobs = jobs.stream().filter(job -> !job.isFav()).toList();
        Page<Job> expected = new PageImpl<>(notFavJobs);
        when(jobRepository.findByJobTitleAndJobDescription(theSearchName, pageable)).thenReturn(expected);
        Page<Job> actual = jobService.searchJobs(theSearchName, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual.getContent()))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceUpdateIsFav() throws Exception {
        Job actual = getJob();
        boolean expected = jobService.updateIsFav(randomBoolean(), actual.getId());
        yakshaAssert(currentTest(),
                (expected ? true : false),
                businessTestFile);
    }

    @Test
    public void testServiceSearchFavJobsWithNullKeys() throws Exception {
        List<Job> jobs = getJobList(5);
        Pageable pageable = PageRequest.of(0, 5);
        List<Job> favJobs = jobs.stream().filter(Job::isFav).toList();
        Page<Job> expected = new PageImpl<>(favJobs);
        when(jobRepository.findByJobTitleAndJobDescription(null, pageable)).thenReturn(expected);
        Page<Job> actual = jobService.searchJobs(null, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual.getContent()))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSearchFavJobsWithKeys() throws Exception {
        String theSearchName = randomStringWithSize(5);
        List<Job> jobs = getJobList(5);
        Pageable pageable = PageRequest.of(0, 5);
        List<Job> favJobs = jobs.stream().filter(Job::isFav).toList();
        Page<Job> expected = new PageImpl<>(favJobs);
        when(jobRepository.findByJobTitleAndJobDescription(theSearchName, pageable)).thenReturn(expected);
        Page<Job> actual = jobService.searchJobs(theSearchName, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual.getContent()))
                        ? "true"
                        : "false"),
                businessTestFile);
    }
}
