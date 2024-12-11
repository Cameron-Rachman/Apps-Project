package com.example.demo.services;


import com.example.demo.entities.Pet;
import com.example.demo.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    @Override
    public Pet updatePetDetails(Long id, String name, int age) {
        Pet pet = getPetById(id);
        pet.setName(name);
        pet.setAge(age);
        return petRepository.save(pet);
    }

    @Override
    public void deletePetById(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public void deletePetsByName(String name) {
        petRepository.deleteByNameIgnoreCase(name);
    }

    @Override
    public List<Pet> findPetsByAnimalType(String animalType) {
        return petRepository.findByAnimalType(animalType);
    }

    @Override
    public List<Pet> findPetsByBreed(String breed) {
        return petRepository.findByBreedOrderByAge(breed);
    }

    @Override
    public List<Object[]> getNameAndBreed() {
        return petRepository.getNameAndBreed();
    }

    @Override
    public Object[] getPetStatistics() {
        return petRepository.getPetStatistics().get(0);
    }
}