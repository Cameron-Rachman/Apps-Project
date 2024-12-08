package com.example.labai.service.impl;

import com.example.labai.model.Pet;
import com.example.labai.repository.PetRepository;
import com.example.labai.service.PetService;
import com.example.labai.dto.PetNameBreedDto;
import com.example.labai.dto.PetStatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Autowired
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
    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    @Override
    public Pet updatePet(Long id, Pet pet) {
        if (petRepository.existsById(id)) {
            pet.setId(id);
            return petRepository.save(pet);
        }
        throw new RuntimeException("Pet not found with id: " + id);
    }

    @Override
    public void deletePetById(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deletePetsByName(String name) {
        petRepository.deleteByNameIgnoreCase(name);
    }

    @Override
    public List<Pet> findPetsByAnimalType(String animalType) {
        return petRepository.findByAnimalTypeIgnoreCase(animalType);
    }

    @Override
    public List<Pet> findPetsByBreed(String breed) {
        return petRepository.findByBreedIgnoreCaseOrderByAgeAsc(breed);
    }

    @Override
    public List<PetNameBreedDto> getPetNameAndBreed() {
        return petRepository.findAllPetNameAndBreed();
    }

    @Override
    public PetStatisticsDto getPetStatistics() {
        Double avgAge = petRepository.getAverageAge();
        Integer oldestAge = petRepository.getOldestAge();
        return new PetStatisticsDto(avgAge, oldestAge);
    }
}