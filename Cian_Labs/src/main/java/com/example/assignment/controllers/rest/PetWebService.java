package com.example.assignment.controllers.rest;

import com.example.assignment.dtos.NewPet;
import com.example.assignment.entities.Pet;
import com.example.assignment.services.PetService;
import com.example.assignment.services.exceptions.InvalidDataException;
import com.example.assignment.services.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/myapi/pets")
public class PetWebService {
    private PetService petService;

    @GetMapping({"/", ""})
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable int id) throws NotFoundException {
        return petService.getPetById(id);
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePetById(@PathVariable("id") int id) throws NotFoundException {
        petService.deletePetById(id);
    }

    @PostMapping({"/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Pet addPet(@RequestBody @Valid NewPet newPet) throws InvalidDataException, NotFoundException {
        if (newPet.household() == null) {
            throw new InvalidDataException("No household, a pet must be associated with a house");
        }
        Pet pet = new Pet(newPet.name(), newPet.animalType(),
                newPet.breed(), newPet.age(), newPet.household());
        return petService.createPet(pet);
    }

    @PatchMapping("/{id}/{newName}")
    @ResponseStatus(HttpStatus.OK)
    public Pet updatePetName(@PathVariable("id") int id, @PathVariable String newName) throws NotFoundException, InvalidDataException {
        return petService.updateName(id, newName);
    }

}