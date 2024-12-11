package com.example.demo.repositories;

import com.example.demo.entities.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseholdRepository extends JpaRepository<Household, String> {
    @Query("SELECT h FROM Household h WHERE h.pets IS EMPTY")
    List<Household> findHouseholdsWithNoPets();

    @Query("SELECT h FROM Household h WHERE h.ownerOccupied = true")
    List<Household> findOwnerOccupiedHouseholds();

    @Query("SELECT COUNT(h) FROM Household h WHERE h.pets IS EMPTY")
    long countEmptyHouseholds();

    @Query("SELECT COUNT(h) FROM Household h WHERE SIZE(h.pets) >= h.maxNumberOfOccupants")
    long countFullHouseholds();

    @Query("SELECT h FROM Household h LEFT JOIN FETCH h.pets WHERE h.eircode = :eircode")
    Household findHouseholdByEircodeWithPets(String eircode);
}