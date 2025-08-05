package com.tsinjo.demo.endpoint.controller;

import com.tsinjo.demo.repository.*;
import com.tsinjo.demo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TsinfoController {
    private static final Logger logger = LoggerFactory.getLogger(TsinfoController.class);
    private final DonationService donationService;
    private final DonationRepository donationRepository;
    private final HelpRepository helpRepository;

    public TsinfoController(DonationService donationService, DonationRepository donationRepository, HelpRepository helpRepository) {
        this.donationService = donationService;
        this.donationRepository = donationRepository;
        this.helpRepository = helpRepository;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        logger.info("Loading index page with donations and helps");
        model.addAttribute("donations", donationRepository.findAllByOrderByDonationDateDesc());
        model.addAttribute("helps", helpRepository.findAllByOrderByAidDateDesc());
        return "index";
    }

    @PostMapping("/donate")
    public String submitDonation(
            @RequestParam String donorEmail,
            @RequestParam String donorFullName,
            @RequestParam double amount,
            @RequestParam String paymentMethod,
            @RequestParam String paymentId) {
        logger.info("Received donation submission: email={}, amount={}", donorEmail, amount);
        donationService.submitDonation(donorEmail, donorFullName, amount, paymentMethod, paymentId);
        return "redirect:/";
    }
}