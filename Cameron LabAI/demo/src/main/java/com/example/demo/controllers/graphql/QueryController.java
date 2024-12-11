package com.example.demo.controllers.graphql;

import com.example.demo.entities.Household;
import com.example.demo.entities.Pet;
import com.example.demo.services.HouseholdService;
import com.example.demo.services.PetService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class QueryController {

    private final HouseholdService householdService;
    private final PetService petService;

    public QueryController(HouseholdService householdService, PetService petService) {
        this.householdService = householdService;
        this.petService = petService;
    }

    @QueryMapping
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @QueryMapping
    public List<Pet> getAllPetsByAnimalType(String animalType) {
        return petService.findPetsByAnimalType(animalType);
    }

    @QueryMapping
    public Household getHousehold(String eircode) {
        return householdService.getHouseholdById(eircode, true);
    }

    @QueryMapping
    public Pet getPet(Long id) {
        return petService.getPetById(id);
    }

    @QueryMapping
    public Map<String, Object> getStatistics() {
        Object[] petStats = petService.getPetStatistics();
        Object[] householdStats = householdService.getHouseholdStatistics();

        return Map.of(
                "averageAge", petStats[0],
                "oldestPetAge", petStats[1],
                "emptyHouseholds", householdStats[0],
                "fullHouseholds", householdStats[1]
        );
    }
}