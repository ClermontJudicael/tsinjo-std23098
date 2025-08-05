package com.tsinjo.demo.repository;

import com.tsinjo.demo.modele.Help;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HelpRepository extends JpaRepository<Help, Long> {
    List<Help> findAllByOrderByAidDateDesc();
}