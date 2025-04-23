package com.example.jobsearch.dal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String text;
    LocalDateTime applicationDate;
    @Setter
    @Enumerated(EnumType.STRING)
    ApplicationStatus status;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    Candidate candidate;
    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    Vacancy vacancy;
    @OneToOne
    @JoinColumn(name = "resume_id")
    Resume resume;


}
