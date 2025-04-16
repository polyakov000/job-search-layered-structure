package com.example.jobsearch.bll.service;


import com.example.jobsearch.dal.entity.Employer;
import com.example.jobsearch.dal.repository.EmployerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerService {
    @Autowired
    EmployerRepo employerRepo;
    public void save(Employer employer){
        employerRepo.save(employer);
    }
    public Employer findByOrganisation(String organisation){
       return employerRepo.findByOrganisation(organisation);
    }
    public Employer findById(Long id){
        return employerRepo.findById(id).orElseThrow(()->new RuntimeException("Employer not found"));
    }
    public Employer findByUsername(String username){
        return employerRepo.findByUsername(username);
    }
}
