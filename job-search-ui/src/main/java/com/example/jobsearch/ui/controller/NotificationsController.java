package com.example.jobsearch.ui.controller;

import com.example.jobsearch.bll.service.ApplicationService;
import com.example.jobsearch.bll.service.ResumeService;
import com.example.jobsearch.bll.service.UserService;
import com.example.jobsearch.dal.entity.*;
import com.example.jobsearch.ui.config.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String notifications(Model model) {
        User currentUser = userService.findByUsername(SecurityUtils.getCurrentUsername());
        if (currentUser instanceof Candidate) {

            List<Resume> resumes = resumeService.findAllByCandidate((Candidate) currentUser);
            List<Application> applications = applicationService.findByCandidate((Candidate) currentUser);
            System.out.println("Заявки кандидата: " + applications);// 🔹 Вывод в лог
            model.addAttribute("resumes", resumes);
            model.addAttribute("applications", applications);
        }
        if (currentUser instanceof Employer) {

            List<Application> employerApplications = applicationService.getApplicationsByEmployer((Employer) currentUser);
            System.out.println("Заявки работодателя: " + employerApplications); // 🔹 Вывод в лог
            model.addAttribute("employerApplications", employerApplications);
        }
        model.addAttribute("user", currentUser);
        return "notifications";
    }
    @PostMapping("/unblock/{id}")
    public ResponseEntity<String> unblockUser(@PathVariable Long id) {
        userService.changeStatus(id, UsersStatus.ACTIVE);
        return ResponseEntity.ok("Пользователь разблокирован!");
    }
    @PostMapping("/update-status/{appId}/{status}")
    public ResponseEntity<String> updateApplicationStatus(@PathVariable Long appId, @PathVariable String status) {
        applicationService.updateStatus(appId, ApplicationStatus.valueOf(status));
        return ResponseEntity.ok("Статус обновлен!");
    }

}
