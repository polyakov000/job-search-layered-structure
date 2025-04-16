package com.example.jobsearch.ui.controller;

import com.example.jobsearch.bll.service.CandidateService;
import com.example.jobsearch.bll.service.EmployerService;
import com.example.jobsearch.bll.service.UserService;
import com.example.jobsearch.bll.service.VacancyService;
import com.example.jobsearch.dal.entity.Candidate;
import com.example.jobsearch.dal.entity.Employer;
import com.example.jobsearch.dal.entity.User;
import com.example.jobsearch.dal.entity.Vacancy;
import com.example.jobsearch.dal.repository.VacancyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class StatisticController {
    @Autowired
    UserService userService;
    @Autowired
    VacancyService vacancyService;
    @Autowired
    CandidateService candidateService;
    @Autowired
    EmployerService employerService;
    @GetMapping("/statistics")
    @Transactional
    public String getStatisticPage(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);



        return "statistics";
    }

    @GetMapping("/statistics/result")
    @ResponseBody
    public Map<String, Object> getStatistic(Principal principal, Model model){
        List<User> users = userService.findALL();
        List<Employer> employers = employerService.findAll();
        List<Candidate> candidates = candidateService.findAll();
        List<Vacancy> vacancies = vacancyService.findAll();
        Map<String, Object> stats = new HashMap<>();
        stats.put("users", users);
        stats.put("employers", employers);
        stats.put("candidates", candidates);
        stats.put("vacancies", vacancies);
        return stats;
    }
}
