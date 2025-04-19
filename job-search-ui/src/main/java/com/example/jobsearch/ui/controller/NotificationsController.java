package com.example.jobsearch.ui.controller;

import com.example.jobsearch.bll.service.ApplicationService;
import com.example.jobsearch.bll.service.ResumeService;
import com.example.jobsearch.bll.service.UserService;
import com.example.jobsearch.dal.entity.Application;
import com.example.jobsearch.dal.entity.Candidate;
import com.example.jobsearch.dal.entity.Resume;
import com.example.jobsearch.dal.entity.User;
import com.example.jobsearch.ui.config.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/notifications")
public class NotificationsController {
    @Autowired
    UserService userService;
    @Autowired
    ResumeService resumeService;
    @Autowired
    ApplicationService applicationService;
    @Transactional
    @GetMapping("")
    public String notications(Model model)
    {
        User currentUser = userService.findByUsername(SecurityUtils.getCurrentUsername());

        if (currentUser instanceof Candidate) {
            List<Resume> resumes = resumeService.findAllByCandidate((Candidate) currentUser);
            model.addAttribute("resumes", resumes);
            List<Application> applications = applicationService.findByCandidate((Candidate) currentUser);
            model.addAttribute("applications", applications);
        } else {
            model.addAttribute("resumes", Collections.emptyList()); // Или обработка для Employer
        }

        model.addAttribute("user",userService.findByUsername(SecurityUtils.getCurrentUsername()));
        return "notifications";
    }
}
