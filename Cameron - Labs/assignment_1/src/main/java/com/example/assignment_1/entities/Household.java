package com.example.assignment_1.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "households")
public class Household {

    @Id
    @Column(name = "eircode")
    private String eircode;
    @Column(name = "number_of_occupants")
    private int numberOfOccupants;
    @Column(name = "max_number_of_occupants")
    private int maxNumberOfOccupants;
    @Column(name = "owner_occupied")
    private boolean ownerOccupied;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference
    private List<Pet> pets;

    public Household(String eircode, int numberOfOccupants, int maxNumberOfOccupants, boolean ownerOccupied) {
        this.eircode = eircode;
        this.numberOfOccupants = numberOfOccupants;
        this.maxNumberOfOccupants = maxNumberOfOccupants;
        this.ownerOccupied = ownerOccupied;
    }

}
