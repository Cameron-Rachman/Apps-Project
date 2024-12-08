package com.example.labai.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(name = "household")
public class Household {
    @Setter
    @Id
    private String eircode;

    @Column(name = "number_of_occupants", nullable = false)
    private Integer numberOfOccupants;

    @Column(name = "max_number_of_occupants", nullable = false)
    private Integer maxNumberOfOccupants;

    @Column(name = "owner_occupied", nullable = false)
    private Boolean ownerOccupied;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<Pet> pets;

}