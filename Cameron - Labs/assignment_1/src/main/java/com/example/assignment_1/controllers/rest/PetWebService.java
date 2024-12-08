package com.example.assignment_1.controllers.rest;

import com.example.assignment_1.dtos.NewHousehold;
import com.example.assignment_1.dtos.NewPet;
import com.example.assignment_1.entities.Household;
import com.example.assignment_1.entities.Pet;
import com.example.assignment_1.services.PetService;
import com.example.assignment_1.services.exceptions.BadDataException;
import com.example.assignment_1.services.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/pets")
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePetById(@PathVariable("id") int id) throws NotFoundException {
        petService.deletePetById(id);
    }

    @PostMapping({"/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Pet addPet(@RequestBody @Valid NewPet newPet) throws BadDataException, NotFoundException {
        if (newPet.household() == null) {
            throw new BadDataException("No household, a pet must be associated with a house");
        }
        Pet pet = new Pet(newPet.name(), newPet.animal_type(),
                newPet.breed(), newPet.age(), newPet.household());
        return petService.createPet(pet);
    }

    @PatchMapping("/{id}/{newName}")
    @ResponseStatus(HttpStatus.OK)
    public Pet updatePetName(@PathVariable("id") int id, @PathVariable String newName) throws NotFoundException, BadDataException {
        return petService.updatePetName(id, newName);
    }

}
