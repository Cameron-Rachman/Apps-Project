package com.example.assignment_1.controllers.rest;

import com.example.assignment_1.dtos.NewHousehold;
import com.example.assignment_1.entities.Household;
import com.example.assignment_1.entities.Pet;
import com.example.assignment_1.services.HouseholdService;
import com.example.assignment_1.services.exceptions.BadDataException;
import com.example.assignment_1.services.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/household")
public class HouseholdWebService {

    private HouseholdService householdService;

    @GetMapping({"/", ""})
    public List<Household> getAllHouseholds() {
        return householdService.findAllHouseholds();
    }

    @GetMapping("/without_pets")
    public List<Household> getHouseholdsWithoutPets() {
        return householdService.findHouseholdsWithoutPets();
    }

    @GetMapping("/{eircode}")
    public Household getHouseholdByEircode(@PathVariable String eircode) throws NotFoundException {
        return householdService.findByEircodeWithPetsEager(eircode);
    }

    @DeleteMapping("/{eircode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHouseholdByEircode(@PathVariable("eircode") String eircode) throws NotFoundException {
        householdService.deleteHouseholdByEircode(eircode);
    }

    @PostMapping({"/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Household addHousehold(@RequestBody @Valid NewHousehold newHousehold) throws BadDataException, NotFoundException {
        if (newHousehold.eircode() == null || newHousehold.eircode().isBlank()) {
            throw new BadDataException("Eircode is blank");
        }
        Household household = new Household(newHousehold.eircode(), newHousehold.number_of_occupants(),
                newHousehold.max_number_of_occupants(), newHousehold.owner_occupied());
        return householdService.createHousehold(household);
    }


}
