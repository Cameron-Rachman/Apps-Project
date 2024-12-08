package com.example.labai.repository;

import com.example.labai.dto.PetNameBreedDto;
import com.example.labai.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    void deleteByNameIgnoreCase(String name);
    List<Pet> findByAnimalTypeIgnoreCase(String animalType);
    List<Pet> findByBreedIgnoreCaseOrderByAgeAsc(String breed);

    @Query("SELECT new com.example.labai.dto.PetNameBreedDto(p.name, p.animalType, p.breed) FROM Pet p")
    List<PetNameBreedDto> findAllPetNameAndBreed();

    @Query("SELECT AVG(p.age) FROM Pet p")
    Double getAverageAge();

    @Query("SELECT MAX(p.age) FROM Pet p")
    Integer getOldestAge();
}

