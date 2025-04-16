package com.example.jobsearch.ui.controller;

import com.example.jobsearch.bll.service.EmployerService;
import com.example.jobsearch.bll.service.UserService;
import com.example.jobsearch.dal.entity.Candidate;
import com.example.jobsearch.dal.entity.Employer;
import com.example.jobsearch.dal.entity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    EmployerService employerService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("/signup")
    public String signuppage(){
        return "signup";
    }
    @PostMapping("/signup")
    public String signup(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            Model model
    ) {
        Candidate candidate = Candidate.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .role(Roles.CANDIDATE)
                .build();
        userService.save(candidate);
        return "signup";
    }
    @GetMapping("/signin")
    public String signinpage(){
        return "signin";
    }

    @PostMapping("/signup/employer")
    public String signupEmployer(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String organisation,
            @RequestParam String description,
            Model model
    ) {
        Employer employer = Employer.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .organisation(organisation)
                .description(description)
                .role(Roles.EMPLOYER)
                .build();
       userService.save(employer);
        return "signup";
    }

}
