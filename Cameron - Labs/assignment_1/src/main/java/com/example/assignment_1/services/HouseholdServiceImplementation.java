package com.example.assignment_1.services;

import com.example.assignment_1.entities.Household;
import com.example.assignment_1.repositories.HouseholdRepository;
import com.example.assignment_1.services.exceptions.BadDataException;
import com.example.assignment_1.services.exceptions.NotFoundException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class HouseholdServiceImplementation implements HouseholdService{

    private HouseholdRepository householdRepository;

    @Override
    public Household findByEircodeWithPetsLazy(String eircode) throws NotFoundException {
        return householdRepository.findById(eircode).orElseThrow(
                () -> new NotFoundException("Eircode not found eircode: " + eircode)
        );
    }

    @Override
    public Household findByEircodeWithPetsEager(String eircode) throws NotFoundException {
        return householdRepository.findById(eircode).orElseThrow(
                () -> new NotFoundException("Eircode not found eircode: " + eircode)
        );
    }

    @Override
    public List<Household> findHouseholdsWithoutPets() {
        return householdRepository.findHouseholdsWithoutPets();
    }

    @Override
    public List<Household> findAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public void deleteHouseholdByEircode(String eircode) throws NotFoundException {
        int petRowsAffected = householdRepository.deletePetsByHouseholdEircode(eircode);
        int householdRowsAffected = householdRepository.deleteHouseholdByEircode(eircode);
        if (householdRowsAffected != 1 || petRowsAffected != 1) {
            throw new NotFoundException("Eircode not found eircode: " + eircode);
        }
    }

    @Override
    public Household createHousehold(Household household) throws BadDataException {
        if (household.getEircode().isBlank()){
            throw new BadDataException("Eircode is Blank");
        }
        return householdRepository.save(household);
    }

}
