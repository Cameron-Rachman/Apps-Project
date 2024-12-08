package com.example.assignment.services;

import com.example.assignment.repositories.HouseholdRepository;
import com.example.assignment.entities.Household;
import com.example.assignment.services.exceptions.InvalidDataException;
import com.example.assignment.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class HouseholdServiceImpl implements HouseholdService {

    private HouseholdRepository householdRepository;


    @Override
    public Household findHouseholdByEircode(String eircode) {
        return householdRepository.findById(eircode).orElseThrow(() -> new NotFoundException("No such household"));
    }

    @Override
    public Household findHouseholdByEircodeAndPets(String eircode) {
        return householdRepository.findByEircode(eircode);
    }

    @Override
    public List<Household> findAllHouseholdsNoPets() {
        return householdRepository.findByPetsIsEmpty();
    }

    @Override
    public List<Household> findAll() {
        return householdRepository.findAll();
    }

    @Override
    public void deleteHouseholdById(String id) throws NotFoundException {
        int rowsAffected = householdRepository.deleteHouseholdByEircode(id);
        if (rowsAffected != 1) throw new NotFoundException("No such household");
    }

    @Override
    @Transactional
    public Household createHousehold(Household household) throws InvalidDataException {
        if (household.getEircode().isEmpty()) {
            throw new InvalidDataException("Eircode is empty");
        }
        return householdRepository.save(household);
    }

    @Override
    public void deleteHouseholdByEircode(String eircode) throws NotFoundException {
        int householdRowsAffected = householdRepository.deleteHouseholdByEircode(eircode);
        if (householdRowsAffected != 1) {
            throw new NotFoundException("Eircode not found eircode: " + eircode);
        }
    }

}





