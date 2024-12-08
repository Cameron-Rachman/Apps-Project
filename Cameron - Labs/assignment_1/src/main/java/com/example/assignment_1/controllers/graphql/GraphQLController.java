package com.example.assignment_1.controllers.graphql;

import com.example.assignment_1.dtos.NewHousehold;
import com.example.assignment_1.entities.Household;
import com.example.assignment_1.entities.Pet;
import com.example.assignment_1.services.HouseholdService;
import com.example.assignment_1.services.PetService;
import com.example.assignment_1.services.exceptions.BadDataException;
import com.example.assignment_1.services.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@AllArgsConstructor
public class GraphQLController {

    private HouseholdService householdService;
    private PetService petService;

    @QueryMapping
    List<Household> findAllHouseholds() {
        return householdService.findAllHouseholds();
    }

    @QueryMapping
    public List<Pet> findAllPetsByAnimalType(@Argument String animalType) throws NotFoundException {
        return petService.getPetsByType(animalType);
    }

    @QueryMapping
    public Household findHouseholdByEircode(@Argument String eircode) throws NotFoundException {
        return householdService.findByEircodeWithPetsEager(eircode);
    }

    @QueryMapping
    public Pet findPetById(@Argument int id) throws NotFoundException {
        return petService.getPetById(id);
    }

    @MutationMapping
    public Household addHousehold(@Valid @Argument NewHousehold newHousehold) throws BadDataException, NotFoundException {
        if (newHousehold.eircode() == null || newHousehold.eircode().isBlank()) {
            throw new BadDataException("Eircode is blank");
        }
        Household household = new Household(newHousehold.eircode(), newHousehold.number_of_occupants(),
                newHousehold.max_number_of_occupants(), newHousehold.owner_occupied());
        return householdService.createHousehold(household);
    }

    @MutationMapping
    public void deleteHousehold(@Argument String eircode) throws NotFoundException {
        householdService.deleteHouseholdByEircode(eircode);
    }

    @MutationMapping
    public void deletePet(@Argument int id) throws NotFoundException {
        petService.deletePetById(id);
    }
}
