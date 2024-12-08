package com.example.assignment.services;

import com.example.assignment.repositories.PetRepository;
import com.example.assignment.entities.Pet;
import com.example.assignment.services.exceptions.InvalidDataException;
import com.example.assignment.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
@AllArgsConstructor
class PetServiceImpl implements PetService {

    private PetRepository petRepository;

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(long petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new InvalidDataException("No pet with id " + petId));
    }

    @Override
    public List<Pet> getPetsByType(String animalType) throws NotFoundException {
        return petRepository.findPetsByAnimalType(animalType).orElseThrow(
                () -> new NotFoundException("No pets with animalType " + animalType));
    }

    @Override
    public List<Pet> getPetsByBreedOrderedByAgeDesc(String breed) throws NotFoundException{
        return petRepository.findPetsByBreedOrderByAgeDesc(breed).orElseThrow(
                () -> new NotFoundException("No pets with breed " + breed));
    }

    @Override
    public List<Pet> getPetsByNameAndBreed() {
        return List.of();
        //Should be record.
    }

    public String getPetStats() {
        float average = petRepository.getAvgAge();
        int oldest = petRepository.getOldestAge();
        String stats = "The average age is: " + average + "\nThe oldest age is: " + oldest;
        return stats;
    }

    @Transactional
    @Override
    public String deletePetById(long id) throws NotFoundException {
        int rowsAffected = petRepository.deleteById(id);
        if (rowsAffected != 1) {
            throw new NotFoundException("No pet with id " + id);
        }
        return "deleted pet with id: " + id;
    }

    @Transactional
    @Override
    public String deletePetByName(String name) throws NotFoundException{
        int rowsAffected = petRepository.deleteByNameIgnoreCase(name);
        if (rowsAffected != 1) {
            throw new NotFoundException("No pet with name " + name);
        }
        return "deleted pet with name: " + name;
    }

    @Transactional
    @Override
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Transactional
    @Override
    public Pet updateName(long id, String name) throws NotFoundException {
        int rowsAffected = petRepository.updateName(id, name);
        if (rowsAffected != 1) {throw new NotFoundException("No pet with id " + id);}
        return getPetById(id);
    }


/*
    @Override
    public List<PetRecord> getPetRecord(){
        return petRepository.findNameBreedType();
    }
*/

    @Override
    public double getAveragePetAge() {
        return petRepository.getAvgAge();
    }

    @Override
    public int getOldestPetAge() {
        return petRepository.getOldestAge();
    }

}