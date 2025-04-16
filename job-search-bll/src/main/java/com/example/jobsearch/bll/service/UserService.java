package com.example.jobsearch.bll.service;

import com.example.jobsearch.dal.entity.User;
import com.example.jobsearch.dal.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public void save(User user){
        userRepo.save(user);
    }
    public User findByUsername(String username){
       return userRepo.findByUsername(username);
    }
    public List<User> findALL(){
        return  userRepo.findAll();
    }

}
