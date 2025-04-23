package com.example.jobsearch.dal.repository;


import com.example.jobsearch.dal.entity.Application;
import com.example.jobsearch.dal.entity.Candidate;
import com.example.jobsearch.dal.entity.Employer;
import com.example.jobsearch.dal.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application,Long> {
    List<Application> findByCandidate(Candidate candidate);
    // 🔹 Найти все заявки по работодателю
    @Query("SELECT a FROM Application a WHERE a.vacancy.employer = :employer ORDER BY a.applicationDate DESC")
    List<Application> findAllByEmployer(@Param("employer") Employer employer);
}
