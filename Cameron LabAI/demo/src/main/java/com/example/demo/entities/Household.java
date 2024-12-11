package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Household {
    @Id
    private String eircode;

    private int numberOfOccupants;
    private int maxNumberOfOccupants;
    private boolean ownerOccupied;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<Pet> pets;

    public void setMaxNumberOfOccupants(int maxNumberOfOccupants) {
        this.maxNumberOfOccupants = maxNumberOfOccupants;
    }

    public void setNumberOfOccupants(int numberOfOccupants) {
        this.numberOfOccupants = numberOfOccupants;
    }

}