package com.example.demo;

import com.example.demo.entities.Household;
import com.example.demo.repositories.HouseholdRepository;
import com.example.demo.services.HouseholdService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private HouseholdService householdService;

    @MockBean
    private HouseholdRepository householdRepository;

    @Test
    void testCreateHousehold() {
        Household household = new Household();
        when(householdRepository.save(household)).thenReturn(household);

        Household result = householdService.createHousehold(household);

        assertNotNull(result);
        verify(householdRepository, times(1)).save(household);
    }

    @Test
    void testGetAllHouseholds() {
        when(householdRepository.findAll()).thenReturn(List.of(new Household()));

        List<Household> result = householdService.getAllHouseholds();

        assertFalse(result.isEmpty());
        verify(householdRepository, times(1)).findAll();
    }

    @Test
    void testGetHouseholdByIdWithoutPets() {
        Household household = new Household();
        when(householdRepository.findById("test-eircode")).thenReturn(Optional.of(household));

        Household result = householdService.getHouseholdById("test-eircode", false);

        assertNotNull(result);
        verify(householdRepository, times(1)).findById("test-eircode");
    }

    @Test
    void testGetHouseholdByIdWithPets() {
        Household household = new Household();
        when(householdRepository.findHouseholdByEircodeWithPets("test-eircode")).thenReturn(household);

        Household result = householdService.getHouseholdById("test-eircode", true);

        assertNotNull(result);
        verify(householdRepository, times(1)).findHouseholdByEircodeWithPets("test-eircode");
    }

    @Test
    void testUpdateHouseholdDetails() {
        Household household = new Household();
        when(householdRepository.findById("test-eircode")).thenReturn(Optional.of(household));
        when(householdRepository.save(household)).thenReturn(household);

        Household result = householdService.updateHouseholdDetails("test-eircode", 3, 5);

        assertEquals(3, household.getNumberOfOccupants());
        assertEquals(5, household.getMaxNumberOfOccupants());
        verify(householdRepository, times(1)).save(household);
    }

    @Test
    void testDeleteHouseholdById() {
        Household household = new Household();
        when(householdRepository.findById("test-eircode")).thenReturn(Optional.of(household));

        householdService.deleteHouseholdById("test-eircode");

        verify(householdRepository, times(1)).delete(household);
    }

}
