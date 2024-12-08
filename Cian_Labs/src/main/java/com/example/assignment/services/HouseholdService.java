package com.example.assignment.services;


import com.example.assignment.entities.Household;
import com.example.assignment.services.exceptions.NotFoundException;

import java.util.List;

public interface HouseholdService {

    Household findHouseholdByEircode(String eircode);

    Household findHouseholdByEircodeAndPets(String eircode);

    List<Household> findAllHouseholdsNoPets();

    List<Household> findAll();

    void deleteHouseholdById(String id) throws NotFoundException;

    Household createHousehold(Household household);

    void deleteHouseholdByEircode(String eircode);
}
