package com.example.AppointmedBackground.Repository;

import com.example.AppointmedBackground.Models.Appuntamenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppuntamentiRepository extends JpaRepository <Appuntamenti, Integer> {
}
