package com.example.assignment_1.repositories;

import com.example.assignment_1.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface PetRepository extends JpaRepository<Pet, Integer>{

    @Modifying
    @Transactional
    @Query("UPDATE Pet pet SET pet.name=:newName WHERE pet.id=:id")
    int updatePetName(@Param("id") int id, @Param("newName") String name);

    @Modifying
    @Transactional
    @Query("UPDATE Pet pet SET pet.age=:newAge WHERE pet.id=:id")
    int updatePetAge(@Param("id") int id, @Param("newAge") int age);

    @Modifying
    @Transactional
    @Query("DELETE FROM Pet pet WHERE pet.id = :id")
    int deletePetById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Pet pet WHERE LOWER(pet.name) = LOWER(:name)")
    int deletePetByName(@Param("name") String name);

    @Query("SELECT pet FROM Pet pet WHERE pet.animalType = :animalType")
    Optional<List<Pet>> findPetsByAnimalType(@Param("animalType") String animalType);

    @Query("SELECT pet FROM Pet pet WHERE pet.breed = :breed ORDER BY pet.age ASC")
    Optional<List<Pet>> findPetsByBreedOrderedByAge(@Param("breed") String breed);

}
