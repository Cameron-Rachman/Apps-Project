package com.example.labai.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HouseholdDto(
        @NotBlank(message = "Eircode is required")
        String eircode,

        @NotNull(message = "Number of occupants is required")
        @Min(value = 1, message = "Number of occupants must be at least 1")
        Integer numberOfOccupants,

        @NotNull(message = "Max number of occupants is required")
        @Min(value = 1, message = "Max number of occupants must be at least 1")
        Integer maxNumberOfOccupants,

        @NotNull(message = "Owner occupied status is required")
        Boolean ownerOccupied
) {}