package com.example.assignment_1.repositories;

import com.example.assignment_1.entities.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HouseholdRepository extends JpaRepository<Household, String> {

    @Query("SELECT household FROM Household household LEFT JOIN household.pets WHERE household.eircode = :eircode")
    Household findByEircodeWithPetsLazy(String eircode);

    @Query("SELECT household FROM Household household LEFT JOIN FETCH household.pets WHERE household.eircode = :eircode")
    Household findByEircodeWithPetsEager(String eircode);

    @Query("SELECT household FROM Household household LEFT JOIN FETCH household.pets pet WHERE pet IS NULL")
    List<Household> findHouseholdsWithoutPets();

    @Modifying
    @Transactional
    @Query("DELETE FROM Pet pet WHERE pet.household.eircode = :eircode")
    int deletePetsByHouseholdEircode(@Param("eircode") String eircode);

    @Modifying
    @Transactional
    @Query("DELETE FROM Household household WHERE household.eircode = :eircode")
    int deleteHouseholdByEircode(@Param("eircode") String eircode);
}
