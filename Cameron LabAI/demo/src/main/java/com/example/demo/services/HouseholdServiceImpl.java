package com.example.demo.services;
import com.example.demo.entities.Household;
import com.example.demo.repositories.HouseholdRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdServiceImpl implements HouseholdService {
    private final HouseholdRepository householdRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    @Override
    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Household getHouseholdById(String eircode, boolean includePets) {
        if (includePets) {
            return householdRepository.findHouseholdByEircodeWithPets(eircode);
        } else {
            return householdRepository.findById(eircode).orElseThrow(() -> new RuntimeException("Household not found"));
        }
    }

    @Override
    public Household updateHouseholdDetails(String eircode, int numberOfOccupants, int maxNumberOfOccupants) {
        Household household = householdRepository.findById(eircode)
                .orElseThrow(() -> new RuntimeException("Household not found"));
        household.setNumberOfOccupants(numberOfOccupants);
        household.setMaxNumberOfOccupants(maxNumberOfOccupants);
        return householdRepository.save(household);
    }

    @Override
    public void deleteHouseholdById(String eircode) {
        Household household = householdRepository.findById(eircode)
                .orElseThrow(() -> new RuntimeException("Household not found"));
        householdRepository.delete(household);
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsWithNoPets();
    }

    @Override
    public List<Household> findOwnerOccupiedHouseholds() {
        return householdRepository.findOwnerOccupiedHouseholds();
    }

    @Override
    public Object[] getHouseholdStatistics() {
        long emptyHouses = householdRepository.countEmptyHouseholds();
        long fullHouses = householdRepository.countFullHouseholds();
        return new Object[]{emptyHouses, fullHouses};
    }
}