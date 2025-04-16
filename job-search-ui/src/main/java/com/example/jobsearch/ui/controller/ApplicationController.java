package com.example.jobsearch.ui.controller;

import com.example.jobsearch.bll.service.ApplicationService;
import com.example.jobsearch.bll.service.ResumeService;
import com.example.jobsearch.bll.service.UserService;
import com.example.jobsearch.dal.entity.Candidate;
import com.example.jobsearch.dal.entity.Resume;
import com.example.jobsearch.ui.config.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;
    @Autowired
    ResumeService resumeService;
    @Autowired
    UserService userService;

    @GetMapping("/resume/my")
    @Transactional
    public List<Resume> getMyResumes(){
        Candidate candidate = (Candidate) userService.findByUsername(SecurityUtils.getCurrentUsername());
        return resumeService.findAllByCandidate(candidate);
    }
}
