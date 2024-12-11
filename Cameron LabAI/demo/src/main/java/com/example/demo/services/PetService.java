package com.example.demo.services;

import com.example.demo.entities.Pet;

import java.util.List;

public interface PetService {
    Pet createPet(Pet pet);

    List<Pet> getAllPets();

    Pet getPetById(Long id);

    Pet updatePetDetails(Long id, String name, int age);

    void deletePetById(Long id);

    void deletePetsByName(String name);

    List<Pet> findPetsByAnimalType(String animalType);

    List<Pet> findPetsByBreed(String breed);

    List<Object[]> getNameAndBreed();

    Object[] getPetStatistics();
}