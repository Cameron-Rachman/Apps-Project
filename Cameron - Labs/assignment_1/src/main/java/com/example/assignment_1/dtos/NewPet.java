package com.example.assignment_1.dtos;


import com.example.assignment_1.entities.Household;
import jakarta.validation.constraints.*;

public record NewPet(
        @NotNull @NotEmpty
        String name,
        @NotNull @NotEmpty
        String animal_type,
        @NotNull
        String breed,
        @NotNull
        @Min(value = 0, message = "Age cannot be less than 0")
        int age,
        @NotNull
        Household household) {
}
