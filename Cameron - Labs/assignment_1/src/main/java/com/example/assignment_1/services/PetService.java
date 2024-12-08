package com.example.assignment_1.services;

import com.example.assignment_1.entities.Pet;
import com.example.assignment_1.services.exceptions.BadDataException;
import com.example.assignment_1.services.exceptions.NotFoundException;

import java.util.List;

public interface PetService {
    Pet createPet(Pet pet) throws BadDataException;
    List<Pet> getAllPets();
    Pet getPetById(int id) throws NotFoundException;
    Pet updatePetName(int id, String name) throws NotFoundException;
    Pet updatePetAge(int id, int age) throws NotFoundException;
    void deletePetById(int id) throws NotFoundException;
    int deletePetByName(String name) throws NotFoundException;
    List<Pet> getPetsByType(String animalType) throws NotFoundException;
    List<Pet> getPetsByBreedOrderedByAge(String breed) throws NotFoundException;
    List<Pet> getNameAndBreedOnly();
    String getPetStatistics();

}