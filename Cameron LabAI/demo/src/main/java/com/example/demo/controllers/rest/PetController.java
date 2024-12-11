package com.example.demo.controllers.rest;

import com.example.demo.entities.Pet;
import com.example.demo.services.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
@Validated
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody @Validated Pet pet) {
        return new ResponseEntity<>(petService.createPet(pet), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pet> updatePetName(@PathVariable Long id, @RequestParam String name, @RequestParam int age) {
        return ResponseEntity.ok(petService.updatePetDetails(id, name, age));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetById(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }
}