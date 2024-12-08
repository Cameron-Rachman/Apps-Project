package com.example.assignment_1.services;

import com.example.assignment_1.entities.Household;
import com.example.assignment_1.entities.Pet;
import com.example.assignment_1.services.exceptions.BadDataException;
import com.example.assignment_1.services.exceptions.NotFoundException;

import java.util.List;

public interface HouseholdService {
    Household findByEircodeWithPetsLazy(String eircode) throws NotFoundException;
    Household findByEircodeWithPetsEager(String eircode) throws NotFoundException;
    List<Household> findHouseholdsWithoutPets();
    List<Household> findAllHouseholds();
    void deleteHouseholdByEircode(String eircode) throws NotFoundException;
    Household createHousehold(Household household) throws BadDataException;
}
