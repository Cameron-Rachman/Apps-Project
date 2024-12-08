package com.example.labai.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetDto(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Animal type is required")
        String animalType,

        @NotBlank(message = "Breed is required")
        String breed,

        @NotNull(message = "Age is required")
        @Min(value = 0, message = "Age must be non-negative")
        Integer age
) {}