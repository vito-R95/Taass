package com.example.AppointmedBackground.Services;

import com.example.AppointmedBackground.Models.Pazienti;

import java.util.List;

public interface PazientiService {
    public void add(Pazienti pazienti);
    public List<Pazienti> getPazienti();
    public Pazienti getPaziente(int id);
    public void delete(int id);
    public void deleteAll();
    public void update(int id,Pazienti pazienti);
    public String getName(int id);
}
