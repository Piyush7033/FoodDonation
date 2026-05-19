package com.fooddonation.system.controller;

import com.fooddonation.system.entity.Donation;
import com.fooddonation.system.service.DonationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins = "http://localhost:3000")
public class DonationController {

    @Autowired
    private DonationService donationService;

    // ADD DONATION
    @PostMapping("/add")
    public Donation addDonation(@RequestBody Donation donation) {
        return donationService.saveDonation(donation);
    }

    // GET ALL DONATIONS
    @GetMapping("/all")
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }

    // GET DONATION HISTORY BY DONOR ID
    @GetMapping("/history/{donorId}")
    public List<Donation> getDonationHistory(
            @PathVariable Long donorId) {

        return donationService.getDonationHistory(donorId);
    }

    // GET DONATION BY ID
    @GetMapping("/{id}")
    public Optional<Donation> getDonationById(
            @PathVariable Long id) {

        return donationService.getDonationById(id);
    }

    // DELETE DONATION
    @DeleteMapping("/delete/{id}")
    public String deleteDonation(@PathVariable Long id) {

        donationService.deleteDonation(id);

        return "Donation deleted successfully";
    }

    // GET DONATIONS BY STATUS
    @GetMapping("/status/{status}")
    public List<Donation> getDonationsByStatus(
            @PathVariable String status) {

        return donationService.getDonationsByStatus(status);
    }

    // GET DONATIONS BY LOCATION
    @GetMapping("/location/{location}")
    public List<Donation> getDonationsByLocation(
            @PathVariable String location) {

        return donationService.getDonationsByLocation(location);
    }

    // SEARCH FOOD BY NAME
    @GetMapping("/search/{foodName}")
    public List<Donation> searchFoodByName(
            @PathVariable String foodName) {

        return donationService.searchFoodByName(foodName);
    }

    // UPDATE DONATION STATUS
    @PutMapping("/update-status/{id}")
    public Donation updateDonationStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return donationService.updateDonationStatus(id, status);
    }
}