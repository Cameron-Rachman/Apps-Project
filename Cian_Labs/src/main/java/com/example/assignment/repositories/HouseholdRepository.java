package com.example.assignment.repositories;

import com.example.assignment.entities.Household;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HouseholdRepository extends JpaRepository<Household, String> {


    @EntityGraph(attributePaths = {"pets"})
    Household findByEircode(String eircode);

    List<Household> findByPetsIsEmpty();

    @EntityGraph(attributePaths = {"pets"})
    List<Household> findAll();

    @Transactional
    @Modifying
    int deleteHouseholdByEircode(String eircode);
}
