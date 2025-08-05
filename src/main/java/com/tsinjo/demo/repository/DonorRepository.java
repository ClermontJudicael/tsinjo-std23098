package com.tsinjo.demo.repository;

import com.tsinjo.demo.modele.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Long> {
}