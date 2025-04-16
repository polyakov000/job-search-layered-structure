package com.example.jobsearch.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "candidates")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Candidate extends User {
    @OneToMany(mappedBy = "candidate")
    List<Application> applications;
    @OneToMany(mappedBy = "candidate")
    List<Resume> resume;
}