package com.yaksha.training.job.controller;


import com.yaksha.training.job.entity.Job;
import com.yaksha.training.job.service.JobService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value = {"/job", "/"})
public class JobController {

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @RequestMapping(value = {"/list", "/"})
    public String listJobs(Model theModel) {
        theModel.addAttribute("jobs", jobService.getJobs());
        return "list-jobs";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        theModel.addAttribute("job", new Job());
        return "job-add";
    }

    @PostMapping("/saveJob")
    public String saveJob(@Valid @ModelAttribute("job") Job theJob, BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            return "job-add";
        }
        jobService.saveJob(theJob);
        return "redirect:/job/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("jobId") Long jobId, Model theModel) {
        theModel.addAttribute("job", jobService.getJob(jobId));
        return "job-add";

    }

    @GetMapping("/showFormForDelete")
    public String showFormForDelete(@RequestParam("jobId") Long jobId, Model theModel) {
        jobService.deleteJob(jobService.getJob(jobId));
        return "redirect:/job/list";

    }
}
