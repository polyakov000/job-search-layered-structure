package com.example.jobsearch.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.jobsearch.dal", "com.example.jobsearch.bll", "com.example.jobsearch.ui"})
@EnableJpaRepositories(basePackages = "com.example.jobsearch.dal.repository")
@EntityScan(basePackages = "com.example.jobsearch.dal.entity")
public class JobSearchApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(JobSearchApplication.class, args);
    }
}

