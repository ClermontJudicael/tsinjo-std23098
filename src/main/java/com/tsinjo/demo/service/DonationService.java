package com.tsinjo.demo.service;

import com.tsinjo.demo.config.*;
import com.tsinjo.demo.repository.*;
import com.tsinjo.demo.modele.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DonationService {
    private static final Logger logger = LoggerFactory.getLogger(DonationService.class);
    private final DonationRepository donationRepository;
    private final DonorRepository donorRepository;
    private final PaymentRepository paymentRepository;
    private final VolaClient volaClient;

    public DonationService(DonationRepository donationRepository, DonorRepository donorRepository,
                           PaymentRepository paymentRepository, VolaClient volaClient) {
        this.donationRepository = donationRepository;
        this.donorRepository = donorRepository;
        this.paymentRepository = paymentRepository;
        this.volaClient = volaClient;
    }

    public Donation submitDonation(String donorEmail, String donorFullName, double amount, String paymentMethod, String paymentId) {
        logger.info("Submitting donation: email={}, amount={}, paymentId={}", donorEmail, amount, paymentId);
        Donor donor = new Donor();
        donor.setEmail(donorEmail);
        donor.setFullName(donorFullName);
        donor = donorRepository.save(donor);

        VolaPaymentRequest request = new VolaPaymentRequest();
        request.setPaymentId(paymentId);
        request.setAmount(amount);
        request.setPaymentMethod(paymentMethod);

        VolaPaymentResponse response = volaClient.submitPayment(request);
        if (!"VERIFYING".equals(response.getStatus())) {
            logger.error("Initial payment status is not VERIFYING: {}", response.getStatus());
            throw new RuntimeException("Payment submission failed");
        }

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus(response.getStatus());
        payment = paymentRepository.save(payment);

        Donation donation = new Donation();
        donation.setDonor(donor);
        donation.setPayment(payment);
        donation.setDonationDate(LocalDateTime.now());
        donation = donationRepository.save(donation);

        logger.info("Donation saved with status VERIFYING: {}", paymentId);
        return donation;
    }
}