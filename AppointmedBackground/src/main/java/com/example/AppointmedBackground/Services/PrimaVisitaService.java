package com.example.AppointmedBackground.Services;

import com.example.AppointmedBackground.Models.PrimaVisita;

import java.util.List;

public interface PrimaVisitaService {
    public List<PrimaVisita> getPrimaVisita();
    public void addPrimaVisita(PrimaVisita primaVisita);
    public PrimaVisita getPV(int id);
    public void delete(int id);
    public void deleteAll();
    public void update(int id,PrimaVisita primaVisita);
    public String app(int id);
    public String getDottore(int id);
    //public String getPaziente(int id);
}
