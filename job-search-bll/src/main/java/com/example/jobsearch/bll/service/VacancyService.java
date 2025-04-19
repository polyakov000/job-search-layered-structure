package com.example.jobsearch.bll.service;


import com.example.jobsearch.dal.entity.Vacancy;
import com.example.jobsearch.dal.repository.VacancyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyService {
    @Autowired
    VacancyRepo vacancyRepo;
    public void save(Vacancy vacancy){
        vacancyRepo.save(vacancy);
    }
    public Vacancy findBySalary(Integer salary){
        return vacancyRepo.findBySalary(salary);
    }
    public Vacancy findByOrganisation(String position){
        return vacancyRepo.findByPosition(position);
    }
    public List<Vacancy> findAll(){
        return vacancyRepo.findAll();
    }

    public Vacancy findById(long vacancyId) {
        return vacancyRepo.findById(vacancyId).orElse(null);
    }

}
