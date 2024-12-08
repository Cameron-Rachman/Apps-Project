package com.example.labai.controller;

import com.example.labai.dto.HouseholdInput;
import com.example.labai.model.Household;
import com.example.labai.model.Pet;
import com.example.labai.service.HouseholdService;
import com.example.labai.service.PetService;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLController {

    private final HouseholdService householdService;
    private final PetService petService;

    @Autowired
    public GraphQLController(HouseholdService householdService, PetService petService) {
        this.householdService = householdService;
        this.petService = petService;
    }

    @QueryMapping
    public List<Household> allHouseholds() {
        return householdService.getAllHouseholds();
    }

    @QueryMapping
    public List<Pet> petsByAnimalType(@Argument String animalType) {
        return petService.findPetsByAnimalType(animalType);
    }

    @QueryMapping
    public Household household(@Argument String eircode) {
        return householdService.getHouseholdById(eircode).orElse(null);
    }

    @QueryMapping
    public Pet pet(@Argument Long id) {
        return petService.getPetById(id).orElse(null);
    }

//    @QueryMapping
//    public Statistics statistics() {
//        return new Statistics(
//                petService.getAveragePetAge(),
//                householdService.getTotalHouseholds(),
//                petService.getTotalPets()
//        );
//    }

    @MutationMapping
    public Household createHousehold(@Argument HouseholdInput input) {
        Household household = new Household();
        household.setEircode(input.getEircode());
        household.setNumberOfOccupants(input.getNumberOfOccupants());
        household.setMaxNumberOfOccupants(input.getMaxNumberOfOccupants());
        household.setOwnerOccupied(input.isOwnerOccupied());
        return householdService.createHousehold(household);
    }

    @MutationMapping
    public Boolean deleteHousehold(@Argument String eircode) {
        householdService.deleteHouseholdById(eircode);
        return true;
    }

    @MutationMapping
    public Boolean deletePet(@Argument Long id) {
        petService.deletePetById(id);
        return true;
    }
}
