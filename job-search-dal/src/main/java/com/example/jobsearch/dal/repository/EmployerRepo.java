package com.example.jobsearch.dal.repository;


import com.example.jobsearch.dal.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepo extends JpaRepository<Employer, Long> {
    Employer findByOrganisation(String organisation);

    Employer findByUsername(String username);
}
