package com.example.AppointmedBackground.Repository;

import com.example.AppointmedBackground.Models.PrimaVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimaVisitaRepository extends JpaRepository<PrimaVisita, Integer> {
}
