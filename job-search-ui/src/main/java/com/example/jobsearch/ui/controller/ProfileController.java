package com.example.jobsearch.ui.controller;

import com.example.jobsearch.bll.service.EmployerService;
import com.example.jobsearch.bll.service.ResumeService;
import com.example.jobsearch.bll.service.UserService;
import com.example.jobsearch.dal.entity.*;
import com.example.jobsearch.ui.config.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/user")
public class ProfileController {
    @Autowired
    UserService userService;
    @Autowired
    EmployerService employerService;
    @Autowired
    ResumeService resumeService;
    @Transactional
    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);

        if (user instanceof Candidate) {
            List<Resume> resumes = resumeService.findAllByCandidate((Candidate) user);
            model.addAttribute("resumes", resumes);
        } else {
            model.addAttribute("resumes", Collections.emptyList()); // Или обработка для Employer
        }
        model.addAttribute("user", user);

        if (user.getRole() == Roles.EMPLOYER) {
            Employer employer = employerService.findByUsername(username);
            model.addAttribute("employer", employer);
        }

        return "profile";
    }

}
