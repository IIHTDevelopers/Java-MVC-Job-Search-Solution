package com.yaksha.training.job.controller;


import com.yaksha.training.job.entity.Job;
import com.yaksha.training.job.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @RequestMapping(value = {"/list", "/", "/search"})
    public String listJobs(@RequestParam(value = "theSearchName", required = false) String theSearchName,
                           @PageableDefault(size = 5) Pageable pageable,
                           Model theModel) {
        Page<Job> jobs = jobService.searchJobs(theSearchName, pageable);
        theModel.addAttribute("jobs", jobs.getContent());
        theModel.addAttribute("theSearchName", theSearchName != null ? theSearchName : "");
        theModel.addAttribute("totalPage", jobs.getTotalPages());
        theModel.addAttribute("page", pageable.getPageNumber());
        theModel.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
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

    @GetMapping("/markFav")
    public String updateIsFav(@RequestParam("isFav") boolean isFav, @RequestParam("jobId") Long jobId, Model theModel) {
        jobService.updateIsFav(isFav, jobId);
        return "redirect:/job/list";

    }

    @RequestMapping("/searchFav")
    public String searchAppointment(@RequestParam(value = "theSearchName", required = false) String theSearchName,
                                    Model theModel,
                                    @PageableDefault(size=5) Pageable pageable) {
        Page<Job> jobs = jobService.searchFavJobs(theSearchName, pageable);
        theModel.addAttribute("jobs", jobs.getContent());
        theModel.addAttribute("theSearchName", theSearchName != null ? theSearchName : "");
        theModel.addAttribute("totalPage", jobs.getTotalPages());
        theModel.addAttribute("page", pageable.getPageNumber());
        theModel.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
        return "fav-jobs";
    }
}
