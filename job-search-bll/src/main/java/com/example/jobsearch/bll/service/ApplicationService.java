package com.example.jobsearch.bll.service;


import com.example.jobsearch.dal.entity.Application;
import com.example.jobsearch.dal.repository.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    @Autowired
    ApplicationRepo applicationRepo;
    public void save(Application application){
        applicationRepo.save(application);
    }
}
