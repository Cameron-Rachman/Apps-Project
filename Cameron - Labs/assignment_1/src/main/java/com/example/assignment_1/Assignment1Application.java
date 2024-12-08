package com.example.assignment_1;

import com.example.assignment_1.repositories.PetRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Assignment1Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Assignment1Application.class, args);
        context.getBean(PetRepository.class).findAll().forEach(System.out::println);
    }

}
