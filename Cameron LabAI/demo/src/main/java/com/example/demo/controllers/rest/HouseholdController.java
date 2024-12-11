package com.example.demo.controllers.rest;

import com.example.demo.entities.Household;
import com.example.demo.services.HouseholdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/households")
@Validated
public class HouseholdController {
    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @GetMapping
    public ResponseEntity<List<Household>> getAllHouseholds() {
        return ResponseEntity.ok(householdService.getAllHouseholds());
    }

    @GetMapping("/{eircode}")
    public ResponseEntity<Household> getHouseholdById(@PathVariable String eircode, @RequestParam(defaultValue = "false") boolean includePets) {
        return ResponseEntity.ok(householdService.getHouseholdById(eircode, includePets));
    }

    @GetMapping("/nopets")
    public ResponseEntity<List<Household>> getHouseholdsWithNoPets() {
        return ResponseEntity.ok(householdService.findHouseholdsWithNoPets());
    }

    @PostMapping
    public ResponseEntity<Household> createHousehold(@RequestBody @Validated Household household) {
        return new ResponseEntity<>(householdService.createHousehold(household), HttpStatus.CREATED);
    }

    @DeleteMapping("/{eircode}")
    public ResponseEntity<Void> deleteHouseholdById(@PathVariable String eircode) {
        householdService.deleteHouseholdById(eircode);
        return ResponseEntity.noContent().build();
    }
}