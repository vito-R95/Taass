package com.example.AppointmedBackground.Services;

import com.example.AppointmedBackground.Models.Messaggi;

import java.util.List;

public interface MessaggiService {
    public void add(Messaggi messaggi);
    public List<Messaggi> getMessaggi();
    public Messaggi getMessaggio(int id);
    public void delete(int id);
    public void deleteAll();
}
