package com.example.jobsearch.ui.controller;

import com.example.jobsearch.bll.service.*;
import com.example.jobsearch.dal.entity.Application;
import com.example.jobsearch.dal.entity.ApplicationStatus;
import com.example.jobsearch.dal.entity.Candidate;
import com.example.jobsearch.dal.entity.Resume;
import com.example.jobsearch.ui.config.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    @Autowired
    CandidateService candidateService;
    @Autowired
    VacancyService vacancyService;

    @GetMapping("/resume/my")
    @Transactional
    public List<Resume> getMyResumes(){
        Candidate candidate = (Candidate) userService.findByUsername(SecurityUtils.getCurrentUsername());
        return resumeService.findAllByCandidate(candidate);
    }
    @PostMapping("/send")
    public void sendApplication(@RequestBody Map<String, Object> requestData) {
        if (!requestData.containsKey("candidateId") || !requestData.containsKey("vacancyId") || !requestData.containsKey("resumeId") || !requestData.containsKey("message")) {
            throw new IllegalArgumentException("Некорректные данные, отсутствуют обязательные поля.");
        }
        Application application = Application.builder()
                .candidate(candidateService.findById(Long.parseLong(requestData.get("candidateId").toString())))
                .vacancy(vacancyService.findById(Long.parseLong(requestData.get("vacancyId").toString())))
                .applicationDate(LocalDateTime.now())
                .status(ApplicationStatus.APPLIED)
                .resume(resumeService.findById(Long.parseLong(requestData.get("resumeId").toString())))
                .text(requestData.get("message").toString())
                .build();


        applicationService.save(application);
    }
}
