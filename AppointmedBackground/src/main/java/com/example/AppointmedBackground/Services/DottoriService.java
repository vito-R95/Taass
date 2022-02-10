package com.example.AppointmedBackground.Services;

import com.example.AppointmedBackground.Models.Dottori;

import java.util.List;

public interface DottoriService {
    public List<Dottori> getDottori();
    public void addDottore(Dottori dottori);
    public Dottori getDottore(int id);
    public void delete(int id);
    public void deleteAll();
    public void update(int id,Dottori dottori);
    public String getName(int id);
}
