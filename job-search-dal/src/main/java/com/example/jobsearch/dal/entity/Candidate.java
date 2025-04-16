package com.example.jobsearch.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @OneToMany(mappedBy = "candidate")
    List<Application> applications;
    @JsonIgnore
    @OneToMany(mappedBy = "candidate")
    List<Resume> resume;
}