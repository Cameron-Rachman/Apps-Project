package com.example.labai.service;

import com.example.labai.model.Household;
import com.example.labai.dto.HouseholdStatisticsDto;

import java.util.List;
import java.util.Optional;

public interface HouseholdService {
    Household createHousehold(Household household);
    List<Household> getAllHouseholds();
    Optional<Household> getHouseholdById(String eircode);
    Optional<Household> getHouseholdByIdWithPets(String eircode);
    Household updateHousehold(String eircode, Household household);
    void deleteHouseholdById(String eircode);
    void deletePetsByName(String name);
    List<Household> findHouseholdsWithNoPets();
    List<Household> findOwnerOccupiedHouseholds();
    HouseholdStatisticsDto getHouseholdStatistics();
    Optional<Household> findHouseholdByEircode(String eircode);
    Optional<Household> findHouseholdByEircodeWithPets(String eircode);
}