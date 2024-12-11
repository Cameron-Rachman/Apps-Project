package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String animalType;
    private String breed;
    private int age;

    @ManyToOne
    @JoinColumn(name = "household_eircode")
    private Household household;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

