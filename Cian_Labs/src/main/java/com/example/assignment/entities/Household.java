package com.example.assignment.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "household")
public class Household {

    @Id
    private String eircode;

    private int numberOfOccupants;
    private int maxNumberOfOccupants;
    private boolean ownerOccupied;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference
    private List<Pet> pets;

    public Household(String eircode, int numberOfOccupants, int maxNumberOfOccupants) {
        this.eircode = eircode;
        this.numberOfOccupants = numberOfOccupants;
        this.maxNumberOfOccupants = maxNumberOfOccupants;
        this.ownerOccupied = false;
    }

    public Household(@NotEmpty @NotNull String eircode, @NotNull @Min(value = 1, message = "someone must live in the household") int i, @NotNull int i1, @NotNull boolean b) {
    }
}
