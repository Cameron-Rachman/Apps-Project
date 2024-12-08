package com.example.assignment_1;

import com.example.assignment_1.entities.Household;
import com.example.assignment_1.entities.Pet;
import com.example.assignment_1.services.HouseholdService;
import com.example.assignment_1.services.exceptions.BadDataException;
import com.example.assignment_1.services.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HouseholdServiceTests {
    @Autowired
    HouseholdService householdService;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(householdService);
    }

    @Test
    void findByEircodeWithPetsLazy_shouldReturnHouseholdWithPets() throws NotFoundException {
        String eircode = "D02XY45";

        Household household = householdService.findByEircodeWithPetsLazy(eircode);
        Assertions.assertNotNull(household);
        Assertions.assertEquals(eircode, household.getEircode());
        Assertions.assertNotNull(household.getPets());
    }

    @Test
    void findByEircodeWithPetsEager_shouldReturnHouseholdWithPets() throws NotFoundException {
        String eircode = "T12AB34";

        Household household = householdService.findByEircodeWithPetsLazy(eircode);
        Assertions.assertNotNull(household);
        Assertions.assertEquals(eircode, household.getEircode());
        Assertions.assertNotNull(household.getPets());
    }

    @Test
    void findHouseholdsWithoutPets_shouldReturnHouseholdsWithNoPets() {
        List<Household> households = householdService.findHouseholdsWithoutPets();
        Assertions.assertNotNull(households);
        Assertions.assertFalse(households.isEmpty());
    }

}
