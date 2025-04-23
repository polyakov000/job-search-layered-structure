package com.example.jobsearch.ui.controller;

import com.example.jobsearch.bll.service.ApplicationService;
import com.example.jobsearch.bll.service.CandidateService;
import com.example.jobsearch.bll.service.EmployerService;
import com.example.jobsearch.bll.service.UserService;
import com.example.jobsearch.dal.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    EmployerService employerService;
    @Autowired
    CandidateService candidateService;
    @Autowired
    ApplicationService applicationService;
    @GetMapping("/users")
    public String getUsersPage(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        List<Employer> employers = employerService.findAll();
        List<Candidate> candidates = candidateService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("employers", employers); // Обратите внимание на название атрибута
        model.addAttribute("candidates", candidates);
        return "admin";
    }
    @PostMapping("/block/{id}")
    public ResponseEntity<String> blockUser(@PathVariable Long id) {
        userService.changeStatus(id, UsersStatus.BLOCKED);
        return ResponseEntity.ok("Пользователь заблокирован!");
    }
}
