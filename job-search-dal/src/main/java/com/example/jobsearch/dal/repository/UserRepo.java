package com.example.jobsearch.dal.repository;

import com.example.jobsearch.dal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
    List<User> findAll();
}
