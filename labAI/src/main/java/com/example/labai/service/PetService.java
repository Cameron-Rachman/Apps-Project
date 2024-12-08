package com.example.labai.service;

import com.example.labai.model.Pet;
import com.example.labai.dto.PetNameBreedDto;
import com.example.labai.dto.PetStatisticsDto;

import java.util.List;
import java.util.Optional;

public interface PetService {
    Pet createPet(Pet pet);
    List<Pet> getAllPets();
    Optional<Pet> getPetById(Long id);
    Pet updatePet(Long id, Pet pet);
    void deletePetById(Long id);
    void deletePetsByName(String name);
    List<Pet> findPetsByAnimalType(String animalType);
    List<Pet> findPetsByBreed(String breed);
    List<PetNameBreedDto> getPetNameAndBreed();
    PetStatisticsDto getPetStatistics();
}