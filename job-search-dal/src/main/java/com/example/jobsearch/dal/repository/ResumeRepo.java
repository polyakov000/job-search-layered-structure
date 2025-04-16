package com.example.jobsearch.dal.repository;


import com.example.jobsearch.dal.entity.Candidate;
import com.example.jobsearch.dal.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepo extends JpaRepository<Resume, Long> {

    Optional<Resume> findById(Long id);
    List<Resume> findAllByCandidate(Candidate candidate);
//    void deleteById(Long id);
}
