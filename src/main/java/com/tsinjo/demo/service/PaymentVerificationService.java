package com.tsinjo.demo.service;

import com.tsinjo.demo.config.*;
import com.tsinjo.demo.modele.*;
import com.tsinjo.demo.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentVerificationService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentVerificationService.class);
    private final DonationRepository donationRepository;
    private final PaymentRepository paymentRepository;
    private final VolaClient volaClient;

    public PaymentVerificationService(DonationRepository donationRepository, PaymentRepository paymentRepository,
                                      VolaClient volaClient) {
        this.donationRepository = donationRepository;
        this.paymentRepository = paymentRepository;
        this.volaClient = volaClient;
    }

    @Scheduled(fixedRate = 60000) // Check every minute
    public void verifyPendingPayments() {
        List<Donation> verifyingDonations = donationRepository.findAll().stream()
                .filter(d -> d.getPayment() != null && "VERIFYING".equals(d.getPayment().getStatus()))
                .toList();

        for (Donation donation : verifyingDonations) {
            logger.info("Checking payment status for donation: {}", donation.getId());
            VolaPaymentResponse response = volaClient.getPaymentStatus(donation.getPayment().getId().toString());
            Payment payment = donation.getPayment();
            payment.setStatus(response.getStatus());
            paymentRepository.save(payment);
            logger.info("Updated payment {} status to {}", payment.getId(), response.getStatus());
        }
    }
}