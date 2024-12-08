package com.example.assignment.repositories;

import com.example.assignment.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<List<Pet>> findPetsByAnimalType(String animalType);
    Optional<List<Pet>> findPetsByBreedOrderByAgeDesc(String breed);

    @Transactional
    @Modifying
    int deleteById(long id);

    @Transactional
    @Modifying
    int deleteByNameIgnoreCase(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Pet p SET p.name=:newName WHERE p.id =:id")
    int updateName(@Param("id") long id, @Param("newName") String name);


    @Query("SELECT AVG(p.age) FROM Pet p")
    float getAvgAge();

    @Query("SELECT MAX(p.age) FROM Pet p")
    int getOldestAge();


}