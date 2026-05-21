package com.fooddonation.system.service;

import com.fooddonation.system.entity.Donation;
import com.fooddonation.system.repository.DonationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    // SAVE DONATION
    public Donation saveDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    // GET ALL DONATIONS
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    // GET DONATION HISTORY BY DONOR ID
    public List<Donation> getDonationHistory(Long donorId) {
        return donationRepository.findByDonorId(donorId);
    }

    // GET DONATION BY ID
    public Optional<Donation> getDonationById(Long id) {
        return donationRepository.findById(id);
    }

    // DELETE DONATION
    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }

    // GET DONATIONS BY STATUS
    public List<Donation> getDonationsByStatus(String status) {
        return donationRepository.findByStatus(status);
    }

    // GET DONATIONS BY LOCATION
    public List<Donation> getDonationsByLocation(String location) {
        return donationRepository.findByLocation(location);
    }

    // SEARCH FOOD BY NAME
    public List<Donation> searchFoodByName(String foodName) {
        return donationRepository
                .findByFoodNameContainingIgnoreCase(foodName);
    }

    // UPDATE DONATION STATUS
    public Donation updateDonationStatus(Long id, String status) {

        Donation donation = donationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Donation not found"));

        donation.setStatus(status);

        return donationRepository.save(donation);
    }
}