package com.example.assignment.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "animal_type")
    private String animalType;
    @Column(name = "breed")
    private String breed;
    @Column(name = "age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "eircode")
    @JsonBackReference
    private Household household;

    public Pet(String name, String animalType, String breed, int age, Household household) {
        this.name = name;
        this.animalType = animalType;
        this.breed = breed;
        this.age = age;
        this.household = household;
    }
}