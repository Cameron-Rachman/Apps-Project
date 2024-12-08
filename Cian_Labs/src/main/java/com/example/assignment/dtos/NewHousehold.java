package com.example.assignment.dtos;

import com.example.assignment.entities.Pet;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record NewHousehold(
        @NotEmpty @NotNull
        String eircode,
        @NotNull @Min(value = 1, message = "someone must live in the household")
        int numberOfOccupants,
        @NotNull
        int maxNumberOfOccupants,
        @NotNull
        boolean ownerOccupied
)
{}
