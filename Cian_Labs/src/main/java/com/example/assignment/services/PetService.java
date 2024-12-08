package com.example.assignment.services;

import com.example.assignment.entities.Pet;
import com.example.assignment.services.exceptions.InvalidDataException;
import com.example.assignment.services.exceptions.NotFoundException;

import java.util.List;

public interface PetService {


    List<Pet> getAllPets();
    Pet getPetById(long petId) throws NotFoundException;
    List<Pet> getPetsByType(String animalType) throws NotFoundException;
    List<Pet> getPetsByBreedOrderedByAgeDesc(String breed) throws NotFoundException;
    List<Pet> getPetsByNameAndBreed();
    double getAveragePetAge();
    int getOldestPetAge();
    String getPetStats();

    String deletePetById(long petId) throws NotFoundException;
    String deletePetByName(String name) throws NotFoundException;


    Pet createPet(Pet pet) throws InvalidDataException;
    Pet updateName(long id, String name) throws NotFoundException;

}