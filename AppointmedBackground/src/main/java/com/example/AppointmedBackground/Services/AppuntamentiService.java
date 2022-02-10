package com.example.AppointmedBackground.Services;

import com.example.AppointmedBackground.Models.Appuntamenti;

import java.util.List;

public interface AppuntamentiService {
    public List<Appuntamenti> getAppuntamenti();
    public void addAppuntamenti(Appuntamenti appuntamenti);
    public Appuntamenti getAppuntamento(int id);
    public void delete(int id);
    public void deleteAll();
    public void update(int id,Appuntamenti appuntamenti);
    public String app(int id);
    public String getDottore(int id);
    public String getPaziente(int id);
}
