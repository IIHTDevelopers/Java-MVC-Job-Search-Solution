package com.yaksha.training.job.service;

import com.yaksha.training.job.entity.Job;
import com.yaksha.training.job.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Job> searchJobs(String searchKey, Pageable pageable) {
        if (searchKey != null && searchKey.trim().length() > 0) {
            searchKey = searchKey != null && searchKey.isEmpty() ? null : searchKey;
            return jobRepository.findByJobTitleAndJobDescription(searchKey, pageable);
        } else {
            return jobRepository.findByJobTitleAndJobDescription(null, pageable);
        }
    }

    public boolean updateIsFav(boolean isFav, Long id) {
        jobRepository.updateIsFav(isFav, id);
        return true;
    }

    public Page<Job> searchFavJobs(String searchKey, Pageable pageable) {
        if (searchKey != null && searchKey.trim().length() > 0) {
            searchKey = searchKey != null && searchKey.isEmpty() ? null : searchKey;
            return jobRepository.findFavJobByJobTitleAndJobDescription(searchKey, pageable);
        } else {
            return jobRepository.findFavJobByJobTitleAndJobDescription(null, pageable);
        }
    }

}
