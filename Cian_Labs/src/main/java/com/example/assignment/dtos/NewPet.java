package com.example.assignment.dtos;

import com.example.assignment.entities.Household;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewPet(
        @NotEmpty @NotNull
        String name,
        @NotEmpty @NotNull
        String animalType,
        @NotEmpty @NotNull
        String breed,
        @NotEmpty @NotEmpty
        @Min(value = 1, message = "pet must be older than 0")
        int age,
        Household household
)
{}
