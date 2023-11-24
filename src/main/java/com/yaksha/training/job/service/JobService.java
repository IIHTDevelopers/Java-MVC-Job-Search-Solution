package com.yaksha.training.job.service;

import com.yaksha.training.job.entity.Job;
import com.yaksha.training.job.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    public Job saveJob(Job theJob) {
        return jobRepository.save(theJob);
    }

    public Job getJob(Long jobId) {
        return jobRepository.findById(jobId).get();
    }

    public boolean deleteJob(Job job) {
        jobRepository.delete(job);
        return true;
    }

}
