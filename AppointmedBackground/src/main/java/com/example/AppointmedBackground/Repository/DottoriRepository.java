package com.example.AppointmedBackground.Repository;

import com.example.AppointmedBackground.Models.Dottori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DottoriRepository  extends JpaRepository<Dottori, Integer> {
}
