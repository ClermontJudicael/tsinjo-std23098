package com.tsinjo.demo.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Help {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Beneficiary beneficiary;
    @OneToOne
    private Payment payment;
    private LocalDateTime aidDate;
    private String accidentDescription;

}