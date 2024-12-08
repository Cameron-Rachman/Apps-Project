package com.example.assignment_1;

import com.example.assignment_1.entities.Household;
import com.example.assignment_1.entities.Pet;
import com.example.assignment_1.services.PetService;
import com.example.assignment_1.services.exceptions.BadDataException;
import com.example.assignment_1.services.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PetServiceTests {
    @Autowired
    PetService petService;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(petService);
    }

    @Test
    public void save_shouldSavePet_returnWithNewId() throws BadDataException, NotFoundException {
        Household household = new Household(
                "P12IUT2",
                2,
                6,
                true
        );

        Pet pet = new Pet(
                "Dog Test",
                "Dog",
                "Alsatian",
                4,
                household
        );

        pet = petService.createPet(pet);
        Assertions.assertNotNull(pet);
        Assertions.assertTrue(pet.getId() > 0);
    }

    @Test
    void findPets_shouldReturnPets() {
        int count = petService.getAllPets().size();
        Assertions.assertEquals(10, count);

    }

    @Test
    void findPetById_shouldFindPetById() throws NotFoundException {
        Pet pet = petService.getPetById(8);
        Assertions.assertNotNull(pet);
        Assertions.assertEquals("Max", pet.getName());
    }

    @Test
    void updatePetName_shouldUpdatePetName() throws NotFoundException {
        petService.updatePetName(7, "New Name");
        Assertions.assertEquals(petService.getPetById(7).getName(), "New Name");
    }

    @Test
    void updatePetAge_shouldUpdatePetAge() throws NotFoundException {
        petService.updatePetAge(7, 3);
        Assertions.assertEquals(petService.getPetById(7).getAge(), 3);
    }

    @Test
    void deletePetById_shouldDeletePetById() throws NotFoundException {
        Assertions.assertNotNull(petService.getPetById(6));
        petService.deletePetById(6);
        Assertions.assertThrows(NotFoundException.class, () -> {
            petService.getPetById(6);
        });
    }

    @Test
    void deletePetByName_shouldDeletePetByName() throws NotFoundException {
        int rowsDeleted = petService.deletePetByName("Mittens");
        Assertions.assertEquals(1, rowsDeleted);

        Assertions.assertThrows(NotFoundException.class, () -> {
            petService.deletePetByName("Mittens");
        });
    }

    @Test
    void findPetsByType_shouldFindPetsByType() throws NotFoundException {
        List<Pet> dogs = petService.getPetsByType("Dog");
        Assertions.assertEquals(3, dogs.size());
    }

    @Test
    void findPetsByBreed_shouldFindPetsByBreed() throws NotFoundException, BadDataException {
        Household household = new Household(
            "P12IUT2",
            2,
            6,
            true
        );

        Pet pet = new Pet(
                "Dog Test",
                "Dog",
                "Golden Retriever",
                4,
                household
        );

        pet = petService.createPet(pet);
        Assertions.assertNotNull(pet);
        Assertions.assertTrue(pet.getId() > 0);

        List<Pet> pets = petService.getPetsByBreedOrderedByAge("Golden Retriever");
        Assertions.assertEquals(2, pets.size());
        Assertions.assertEquals("Buddy", pets.get(0).getName());
        Assertions.assertEquals("Dog Test", pets.get(1).getName());
    }


}
