package com.yaksha.training.job.boundary;


import com.yaksha.training.job.entity.Job;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.yaksha.training.job.utils.MasterData.getJob;
import static com.yaksha.training.job.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.job.utils.TestUtils.boundaryTestFile;
import static com.yaksha.training.job.utils.TestUtils.currentTest;
import static com.yaksha.training.job.utils.TestUtils.testReport;
import static com.yaksha.training.job.utils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testJobTitleNotBlank() throws Exception {
        Job job = getJob();
        job.setJobTitle("");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testJobTitleNotNull() throws Exception {
        Job job = getJob();
        job.setJobTitle(null);
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testJobTitleMinTwo() throws Exception {
        Job job = getJob();
        job.setJobTitle(randomStringWithSize(1));
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testJobTitleMaxForty() throws Exception {
        Job job = getJob();
        job.setJobTitle(randomStringWithSize(41));
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testJobDescriptionNotBlank() throws Exception {
        Job job = getJob();
        job.setJobDescription("");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testJobDescriptionNotNull() throws Exception {
        Job job = getJob();
        job.setJobDescription(null);
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testJobDescriptionMinTwo() throws Exception {
        Job job = getJob();
        job.setJobDescription(randomStringWithSize(1));
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testJobDescriptionMaxTwoHundred() throws Exception {
        Job job = getJob();
        job.setJobDescription(randomStringWithSize(201));
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testExperienceNotNull() throws Exception {
        Job job = getJob();
        job.setExperience(null);
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testSalaryNotNull() throws Exception {
        Job job = getJob();
        job.setSalary(null);
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }


}
