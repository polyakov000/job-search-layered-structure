package com.example.jobsearch.bll.service;


import com.example.jobsearch.dal.entity.Application;
import com.example.jobsearch.dal.entity.ApplicationStatus;
import com.example.jobsearch.dal.entity.Candidate;
import com.example.jobsearch.dal.entity.Employer;
import com.example.jobsearch.dal.repository.ApplicationRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    ApplicationRepo applicationRepo;
    public void save(Application application){
            applicationRepo.save(application);
    }
    public List<Application> findByCandidate(Candidate candidate) {
        List<Application> applications = applicationRepo.findByCandidate(candidate);
        System.out.println("🎯 Заявки кандидата: " + applications);
        return applications;
    }

    public List<Application> getApplicationsByEmployer(Employer employer) {
        List<Application> applications = applicationRepo.findAllByEmployer(employer);
        System.out.println("🎯 Заявки работодателя: " + applications);
        return applications;
    }


    // 🔹 Обновление статуса заявки
    public void updateStatus(Long applicationId, ApplicationStatus newStatus) {
        Application application = applicationRepo.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Заявка не найдена"));

        application.setStatus(newStatus);
        applicationRepo.save(application);
    }
}
