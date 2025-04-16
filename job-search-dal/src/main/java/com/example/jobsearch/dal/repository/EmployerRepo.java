package com.example.jobsearch.dal.repository;


import com.example.jobsearch.dal.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployerRepo extends JpaRepository<Employer, Long> {
    Employer findByOrganisation(String organisation);

    Employer findByUsername(String username);

    @Override
    List<Employer> findAll();
}
