package com.example.jobsearch.bll.service;

import com.example.jobsearch.dal.entity.Candidate;
import com.example.jobsearch.dal.entity.Resume;
import com.example.jobsearch.dal.repository.ResumeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeService {
    @Autowired
    ResumeRepo resumeRepo;
    public void save(Resume resume){
        resumeRepo.save(resume);
    }
    public Resume findById(Long id){
        return resumeRepo.findById(id).orElse(null);
    }
    public List<Resume> findAllByCandidate(Candidate candidate){
        return resumeRepo.findAllByCandidate(candidate);
    }
    public void deleteById(Long id){
        resumeRepo.deleteById(id);
    }
}
