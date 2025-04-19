package com.example.jobsearch.bll.service;

import com.example.jobsearch.dal.entity.Candidate;
import com.example.jobsearch.dal.repository.CandidateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {
    @Autowired
    CandidateRepo candidateRepo;
    public List<Candidate> findAll(){
        return candidateRepo.findAll();
    }

    public Candidate findById(long candidateId) {
        return candidateRepo.findById(candidateId).orElse(null);
    }
}
