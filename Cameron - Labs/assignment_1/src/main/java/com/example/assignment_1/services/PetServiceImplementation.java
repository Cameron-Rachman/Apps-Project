package com.example.assignment_1.services;


import com.example.assignment_1.entities.Pet;
import com.example.assignment_1.repositories.PetRepository;
import com.example.assignment_1.services.exceptions.BadDataException;
import com.example.assignment_1.services.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class PetServiceImplementation implements PetService {

    private PetRepository petRepository;


    @Transactional
    @Override
    public Pet createPet(Pet pet) throws BadDataException{
        if (pet.getName().isBlank()){
            throw new BadDataException("Pet Name is Blank");
        }
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(int id) throws NotFoundException {
        return petRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Pet not found ID: " + id)
        );
    }

    @Transactional
    @Override
    public Pet updatePetName(int id, String name) throws NotFoundException {
        int rowsAffected = petRepository.updatePetName(id, name);
        if (rowsAffected != 1)
            throw new NotFoundException("Pet not found ID: " + id);
        return getPetById(id);
    }

    @Transactional
    @Override
    public Pet updatePetAge(int id, int age) throws NotFoundException {
        int rowsAffected = petRepository.updatePetAge(id, age);
        if (rowsAffected != 1)
            throw new NotFoundException("Pet not found ID: " + id);
        return getPetById(id);
    }


    @Transactional
    @Override
    public void deletePetById(int id) throws NotFoundException {
        int rowsAffected = petRepository.deletePetById(id);
        if (rowsAffected != 1) {
            throw new NotFoundException("Pet not found ID: " + id);
        }
    }

    @Transactional
    @Override
    public int deletePetByName(String name) throws NotFoundException {
        int rowsAffected = petRepository.deletePetByName(name);
        if (rowsAffected != 1) {
            throw new NotFoundException("Pet not found name: " + name);
        }
        return rowsAffected;
    }

    @Override
    public List<Pet> getPetsByType(String animalType) throws NotFoundException {
        return petRepository.findPetsByAnimalType(animalType).orElseThrow(
                () -> new NotFoundException("Pet type not found ID: " + animalType)
        );
    }

    @Override
    public List<Pet> getPetsByBreedOrderedByAge(String breed) throws NotFoundException {
        return petRepository.findPetsByBreedOrderedByAge(breed).orElseThrow(
                () -> new NotFoundException("Pet type not found ID: " + breed)
        );
    }

    @Override
    public List<Pet> getNameAndBreedOnly() {
        return null;
    }

    @Override
    public String getPetStatistics() {

        return null;
    }
}
