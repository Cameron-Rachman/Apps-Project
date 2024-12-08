package com.example.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AssignmentApplication {

    public static void main(String[] args) {
        //SpringApplication.run(AssignmentApplication.class, args);

    ApplicationContext context = SpringApplication.run(AssignmentApplication.class, args);

    }

}
