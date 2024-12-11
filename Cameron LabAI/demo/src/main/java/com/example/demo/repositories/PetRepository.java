package com.example.demo.repositories;

import com.example.demo.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    void deleteByNameIgnoreCase(String name);

    List<Pet> findByAnimalType(String animalType);

    List<Pet> findByBreedOrderByAge(String breed);

    @Query("SELECT p.name, p.animalType, p.breed FROM Pet p")
    List<Object[]> getNameAndBreed();

    @Query("SELECT AVG(p.age), MAX(p.age) FROM Pet p")
    List<Object[]> getPetStatistics();
}