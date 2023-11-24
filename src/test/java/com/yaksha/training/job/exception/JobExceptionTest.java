package com.yaksha.training.job.exception;

import com.yaksha.training.job.controller.JobController;
import com.yaksha.training.job.entity.Job;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static com.yaksha.training.job.utils.MasterData.getJob;
import static com.yaksha.training.job.utils.TestUtils.currentTest;
import static com.yaksha.training.job.utils.TestUtils.exceptionTestFile;
import static com.yaksha.training.job.utils.TestUtils.testReport;
import static com.yaksha.training.job.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class JobExceptionTest {

    @InjectMocks
    private JobController jobController;

    private MockMvc mockMvc;

    BindingResult bindingResult = mock(BindingResult.class);

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
    public void testExceptionSaveJobTitleAsNull() throws Exception {
        Job job = getJob();
        job.setJobTitle(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveJob")
                .flashAttr("job", job)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("job-add")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveJobDescriptionAsNull() throws Exception {
        Job job = getJob();
        job.setJobDescription(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveJob")
                .flashAttr("job", job)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("job-add")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveJobExperienceAsNull() throws Exception {
        Job job = getJob();
        job.setExperience(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveJob")
                .flashAttr("job", job)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("job-add")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveJobSalaryAsNull() throws Exception {
        Job job = getJob();
        job.setSalary(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveJob")
                .flashAttr("job", job)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("job-add")), exceptionTestFile);
    }

}
