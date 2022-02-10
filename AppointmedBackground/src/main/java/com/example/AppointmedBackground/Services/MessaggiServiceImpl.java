package com.example.AppointmedBackground.Services;

import com.example.AppointmedBackground.Models.Messaggi;
import com.example.AppointmedBackground.Repository.MessaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("messaggiService")
public class MessaggiServiceImpl implements MessaggiService{
    @Autowired
    MessaggiRepository messaggiRepository;
    public MessaggiServiceImpl(){
        System.out.println("Service Messaggi is created");
    }

    @Override
    public void add(Messaggi messaggi) {
        messaggiRepository.save(messaggi);
    }

    @Override
    public List<Messaggi> getMessaggi() {
        return messaggiRepository.findAll();
    }

    @Override
    public Messaggi getMessaggio(int id) {
        Optional<Messaggi> optionalMessaggi =messaggiRepository.findById(id);
        if (optionalMessaggi.isPresent()){
            return optionalMessaggi.get();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        messaggiRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        messaggiRepository.deleteAll();
    }
}
