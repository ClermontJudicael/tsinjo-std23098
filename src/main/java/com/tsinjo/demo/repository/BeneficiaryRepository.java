package com.tsinjo.demo.repository;

import com.tsinjo.demo.modele.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
}