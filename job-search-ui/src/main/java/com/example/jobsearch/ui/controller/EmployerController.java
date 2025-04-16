package com.example.jobsearch.ui.controller;


import com.example.jobsearch.bll.service.EmployerService;
import com.example.jobsearch.bll.service.UserService;
import com.example.jobsearch.dal.entity.Employer;
import com.example.jobsearch.ui.config.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    EmployerService employerService;
    @Autowired
    UserService userService;
    @GetMapping("/{id}")
    public String employer(@PathVariable Long id, Model model){
        Employer employer = employerService.findById(id);
        model.addAttribute("employer",employer);
        model.addAttribute("user",userService.findByUsername(SecurityUtils.getCurrentUsername()));
        return "employer";
    }
}
