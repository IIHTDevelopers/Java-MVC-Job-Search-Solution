package com.yaksha.training.job.functional;

import com.yaksha.training.job.controller.JobController;
import com.yaksha.training.job.entity.Job;
import com.yaksha.training.job.service.JobService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.yaksha.training.job.utils.MasterData.getJob;
import static com.yaksha.training.job.utils.MasterData.getJobList;
import static com.yaksha.training.job.utils.TestUtils.businessTestFile;
import static com.yaksha.training.job.utils.TestUtils.currentTest;
import static com.yaksha.training.job.utils.TestUtils.testReport;
import static com.yaksha.training.job.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class JobControllerTest {

    @Mock
    private JobService jobService;

    @InjectMocks
    private JobController jobController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(jobController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testControllerListJobsHome() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/")).andReturn();
        when(jobService.getJobs()).thenReturn(getJobList(5));
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("list-jobs"), businessTestFile);
    }

    @Test
    public void testControllerListJobs() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/list")).andReturn();
        when(jobService.getJobs()).thenReturn(getJobList(5));
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("list-jobs"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForAdd() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/showFormForAdd")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("job-add"), businessTestFile);
    }

    @Test
    public void testControllerSaveJob() throws Exception {
        Job job = getJob();
        MvcResult result = this.mockMvc.perform(post("/saveJob")
                .flashAttr("job", job)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/job/list"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForUpdate() throws Exception {
        Job job = getJob();
        when(jobService.getJob(job.getId())).thenReturn(job);
        MvcResult result = this.mockMvc.perform(get("/showFormForUpdate")
                .param("jobId", job.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("job-add"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForDeleteJob() throws Exception {
        Job job = getJob();
        MvcResult result = this.mockMvc.perform(get("/showFormForDelete")
                .param("jobId", job.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/job/list"), businessTestFile);
    }

}
