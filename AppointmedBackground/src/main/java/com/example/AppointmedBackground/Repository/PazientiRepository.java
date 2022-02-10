package com.example.AppointmedBackground.Repository;

import com.example.AppointmedBackground.Models.Pazienti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PazientiRepository extends JpaRepository<Pazienti, Integer> {
}
