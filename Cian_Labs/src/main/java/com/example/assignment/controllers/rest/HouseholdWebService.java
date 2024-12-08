package com.example.assignment.controllers.rest;


import com.example.assignment.dtos.NewHousehold;
import com.example.assignment.entities.Household;
import com.example.assignment.services.HouseholdService;
import com.example.assignment.services.exceptions.InvalidDataException;
import com.example.assignment.services.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/myapi/households")
public class HouseholdWebService {

    private HouseholdService householdService;

    @GetMapping({"/", ""})
    public List<Household> findAllHouseholds() {
        return householdService.findAll();
    }

    @GetMapping("/no_pets")
    public List<Household> getHouseholdsWithoutPets() {
        return householdService.findAllHouseholdsNoPets();
    }

    @GetMapping("/{eircode}")
    public Household getHouseholdByEircode(@PathVariable String eircode) throws NotFoundException {
        return householdService.findHouseholdByEircodeAndPets(eircode);
    }

    @DeleteMapping("/{eircode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHouseholdByEircode(@PathVariable("eircode") String eircode) throws NotFoundException {
        householdService.deleteHouseholdByEircode(eircode);
    }

    @PostMapping({"/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Household addHousehold(@RequestBody @Valid NewHousehold newHousehold) throws InvalidDataException, NotFoundException {
        if (newHousehold.eircode() == null || newHousehold.eircode().isEmpty()) {
            throw new InvalidDataException("Eircode is empty and/or missing");
        }
        Household household = new Household(newHousehold.eircode(), newHousehold.numberOfOccupants(),
                newHousehold.maxNumberOfOccupants(), newHousehold.ownerOccupied());
        return householdService.createHousehold(household);
    }


}