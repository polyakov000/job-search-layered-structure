package com.example.jobsearch.dal.repository;

import com.example.jobsearch.dal.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepo extends JpaRepository<Vacancy,Long> {
    Vacancy findByPosition(String position);
    Vacancy findBySalary(Integer salary);

    @Override
    List<Vacancy> findAll();
}
