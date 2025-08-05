package com.tsinjo.demo.repository;

import com.tsinjo.demo.modele.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
