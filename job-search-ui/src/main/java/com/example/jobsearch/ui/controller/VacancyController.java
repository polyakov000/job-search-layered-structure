package com.example.jobsearch.ui.controller;

import com.example.jobsearch.bll.service.ResumeService;
import com.example.jobsearch.bll.service.UserService;
import com.example.jobsearch.bll.service.VacancyService;
import com.example.jobsearch.dal.entity.*;
import com.example.jobsearch.ui.config.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/vacancy")
public class VacancyController {
    @Autowired
    VacancyService vacancyService;
    @Autowired
    UserService userService;
    @Autowired
    ResumeService resumeService;
    @GetMapping("/create")
    public String createVacancyPage(Model model){
        model.addAttribute("user",userService.findByUsername(SecurityUtils.getCurrentUsername()));
        return "createVacancy";
    }
    @PostMapping("/create")
    public String createVacancy(@RequestParam String position, @RequestParam String salary, @RequestParam String description, Principal principal){
        User currentUser = userService.findByUsername(principal.getName());
        Vacancy vacancy = Vacancy.builder()
                                .employer((Employer) currentUser)
                                .position(position)
                                .salary(Integer.parseInt(salary))
                                .description(description).build();
        vacancyService.save(vacancy);
        return "redirect:find";
    }
    @Transactional
    @GetMapping("/find")
    public String findVacancy(Model model, Principal principal){
        List<Vacancy> vacancies = vacancyService.findAll();
        User currentUser = userService.findByUsername(principal.getName());

        if (currentUser instanceof Candidate) {
            List<Resume> resumes = resumeService.findAllByCandidate((Candidate) currentUser);
            model.addAttribute("resumes", resumes);
        } else {
            model.addAttribute("resumes", Collections.emptyList()); // Или обработка для Employer
        }
        model.addAttribute("vacancies",vacancies);
        model.addAttribute("user",currentUser);
        return "vacancies";
    }

}
