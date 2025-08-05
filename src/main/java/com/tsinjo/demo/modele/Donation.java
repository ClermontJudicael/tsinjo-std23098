package com.tsinjo.demo.modele;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Donor donor;
    @OneToOne
    private Payment payment;
    private LocalDateTime donationDate;

}