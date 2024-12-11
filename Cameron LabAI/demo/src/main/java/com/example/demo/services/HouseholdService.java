package com.example.demo.services;

import com.example.demo.entities.Household;

import java.util.List;

public interface HouseholdService {
    Household createHousehold(Household household);

    List<Household> getAllHouseholds();

    Household getHouseholdById(String eircode, boolean includePets);

    Household updateHouseholdDetails(String eircode, int numberOfOccupants, int maxNumberOfOccupants);

    void deleteHouseholdById(String eircode);

    List<Household> findHouseholdsWithNoPets();

    List<Household> findOwnerOccupiedHouseholds();

    Object[] getHouseholdStatistics();
}