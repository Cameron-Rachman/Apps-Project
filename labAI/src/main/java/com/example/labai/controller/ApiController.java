package com.example.labai.controller;

import com.example.labai.dto.HouseholdDto;
import com.example.labai.dto.PetDto;
import com.example.labai.model.Household;
import com.example.labai.model.Pet;
import com.example.labai.service.HouseholdService;
import com.example.labai.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final PetService petService;
    private final HouseholdService householdService;

    @Autowired
    public ApiController(PetService petService, HouseholdService householdService) {
        this.petService = petService;
        this.householdService = householdService;
    }

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }



    @GetMapping("/households")
    public ResponseEntity<List<Household>> getAllHouseholds() {
        return ResponseEntity.ok(householdService.getAllHouseholds());
    }

    @GetMapping("/households/no-pets")
    public ResponseEntity<List<Household>> getHouseholdsWithNoPets() {
        return ResponseEntity.ok(householdService.findHouseholdsWithNoPets());
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable Long id) {
        return petService.getPetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/households/{eircode}")
    public ResponseEntity<Household> getHousehold(@PathVariable String eircode) {
        return householdService.getHouseholdById(eircode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/pets/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/households/{eircode}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteHousehold(@PathVariable String eircode) {
        householdService.deleteHouseholdById(eircode);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/households")
    public ResponseEntity<Household> createHousehold(@Valid @RequestBody HouseholdDto householdDto) {
        Household household = new Household();
        household.setEircode(householdDto.eircode());
        household.setNumberOfOccupants(householdDto.numberOfOccupants());
        household.setMaxNumberOfOccupants(householdDto.maxNumberOfOccupants());
        household.setOwnerOccupied(householdDto.ownerOccupied());
        return ResponseEntity.status(HttpStatus.CREATED).body(householdService.createHousehold(household));
    }

    @PostMapping("/pets")
    public ResponseEntity<Pet> createPet(@Valid @RequestBody PetDto petDto) {
        Pet pet = new Pet();
        pet.setName(petDto.name());
        pet.setAnimalType(petDto.animalType());
        pet.setBreed(petDto.breed());
        pet.setAge(petDto.age());
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.createPet(pet));
    }

    @PatchMapping("/pets/{id}/name")
    public ResponseEntity<Pet> changePetName(@PathVariable Long id, @RequestBody String newName) {
        return petService.getPetById(id)
                .map(pet -> {
                    pet.setName(newName);
                    return ResponseEntity.ok(petService.updatePet(id, pet));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}