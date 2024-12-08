package com.example.labai.service.impl;

import com.example.labai.model.Household;
import com.example.labai.repository.HouseholdRepository;
import com.example.labai.service.HouseholdService;
import com.example.labai.dto.HouseholdStatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepository householdRepository;

    @Autowired
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
    public Optional<Household> getHouseholdById(String eircode) {
        return householdRepository.findById(eircode);
    }

    @Override
    public Optional<Household> getHouseholdByIdWithPets(String eircode) {
        return Optional.ofNullable(householdRepository.findByEircodeWithPets(eircode));
    }

    @Override
    @Transactional
    public Household updateHousehold(String eircode, Household household) {
        if (householdRepository.existsById(eircode)) {
            household.setEircode(eircode);
            if (household.getNumberOfOccupants() > household.getMaxNumberOfOccupants()) {
                throw new IllegalArgumentException("Number of occupants cannot exceed maximum number of occupants");
            }
            return householdRepository.save(household);
        }
        throw new RuntimeException("Household not found with eircode: " + eircode);
    }

    @Override
    @Transactional
    public void deleteHouseholdById(String eircode) {
        householdRepository.deleteById(eircode);
    }

    @Override
    @Transactional
    public void deletePetsByName(String name) {
        List<Household> households = householdRepository.findAllWithPets();
        for (Household household : households) {
            household.getPets().removeIf(pet -> pet.getName().equalsIgnoreCase(name));
            householdRepository.save(household);
        }
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsWithNoPets();
    }

    @Override
    public List<Household> findOwnerOccupiedHouseholds() {
        return householdRepository.findByOwnerOccupiedTrue();
    }

    @Override
    public HouseholdStatisticsDto getHouseholdStatistics() {
        Long emptyHouses = householdRepository.countEmptyHouses();
        Long fullHouses = householdRepository.countFullHouses();
        return new HouseholdStatisticsDto(emptyHouses, fullHouses);
    }

    @Override
    public Optional<Household> findHouseholdByEircode(String eircode) {
        return householdRepository.findById(eircode);
    }

    @Override
    public Optional<Household> findHouseholdByEircodeWithPets(String eircode) {
        return Optional.ofNullable(householdRepository.findByEircodeWithPets(eircode));
    }
}