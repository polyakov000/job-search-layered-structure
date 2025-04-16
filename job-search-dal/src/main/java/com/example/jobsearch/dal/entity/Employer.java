package com.example.jobsearch.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "employers")
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Employer extends User{
    String organisation;
    String description;
    @OneToMany(mappedBy = "employer")
    List<Vacancy> vacancies;

}
