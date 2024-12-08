package com.example.assignment_1.dtos;

import jakarta.validation.constraints.*;

public record NewHousehold(
        @NotEmpty @NotNull
        @Size(min = 7, max = 7, message = "Eircode must be exactly 7 characters long")
        String eircode,
        @NotNull
        int number_of_occupants,
        @NotNull
        int max_number_of_occupants,
        boolean owner_occupied) {
}
