package com.example.demo.controllers.graphql;

import com.example.demo.entities.Household;
import com.example.demo.services.HouseholdService;
import com.example.demo.services.PetService;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MutationController {

    private final HouseholdService householdService;
    private final PetService petService;

    public MutationController(HouseholdService householdService, PetService petService) {
        this.householdService = householdService;
        this.petService = petService;
    }

    @MutationMapping
    public Household createNewHousehold(Household household) {
        return householdService.createHousehold(household);
    }

    @MutationMapping
    public boolean deleteHouseholdById(String eircode) {
        householdService.deleteHouseholdById(eircode);
        return true;
    }

    @MutationMapping
    public boolean deletePetById(Long id) {
        petService.deletePetById(id);
        return true;
    }
}