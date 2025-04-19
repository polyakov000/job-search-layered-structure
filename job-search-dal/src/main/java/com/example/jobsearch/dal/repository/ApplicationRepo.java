package com.example.jobsearch.dal.repository;


import com.example.jobsearch.dal.entity.Application;
import com.example.jobsearch.dal.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application,Long> {
    List<Application> findByCandidate(Candidate candidate);
}
