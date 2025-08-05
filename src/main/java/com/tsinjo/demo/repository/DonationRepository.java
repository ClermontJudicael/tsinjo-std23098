package com.tsinjo.demo.repository;

import com.tsinjo.demo.modele.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findAllByOrderByDonationDateDesc();
}