package com.example.jobsearch.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long ID;
    String position;
    String description;
    Double workExperience;
    @Setter
    @Lob
    byte[] file;
    @Setter
    String fileName;
    @JsonIgnore
    @OneToOne(mappedBy = "resume")
    Application application;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    Candidate candidate;
}

