package com.example.AppointmedBackground.Repository;

import com.example.AppointmedBackground.Models.Messaggi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessaggiRepository extends JpaRepository<Messaggi, Integer> {
}
