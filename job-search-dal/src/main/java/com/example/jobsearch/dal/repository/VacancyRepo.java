package com.example.jobsearch.dal.repository;

import com.example.jobsearch.dal.entity.Vacancy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepo extends JpaRepository<Vacancy,Long> {
    Vacancy findByPosition(String position);
    Vacancy findBySalary(Integer salary);

    @Override
    List<Vacancy> findAll();
    @Query("SELECT v FROM Vacancy v WHERE SIZE(v.applications) > 0 ORDER BY SIZE(v.applications) DESC")
    List<Vacancy> findTopVacanciesWithApplications(Pageable pageable);
}
