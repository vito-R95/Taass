package com.example.AppointmedBackground.Services;

import com.example.AppointmedBackground.Models.Dottori;
import com.example.AppointmedBackground.Repository.DottoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dottoriService")
public class DottoriServiceImpl implements DottoriService{
    @Autowired
    private DottoriRepository dottoriRepository;
    public DottoriServiceImpl(){
        System.out.println("Service Dottori is created");
    }

    @Override
    public List<Dottori> getDottori() {
        return (List<Dottori>) dottoriRepository.findAll();
    }

    @Override
    public void addDottore(Dottori dottori) {
        dottoriRepository.save(dottori);
    }

    @Override
    public Dottori getDottore(int id) {
        Optional<Dottori> optionalDottori=dottoriRepository.findById(id);
        if (optionalDottori.isPresent()){
            return optionalDottori.get();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        dottoriRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        dottoriRepository.deleteAll();
    }

    @Override
    public void update(int id, Dottori dottori) {
        Optional<Dottori> optionalDottori=dottoriRepository.findById(id);
        if (optionalDottori.isPresent()){
            Dottori _dottori= optionalDottori.get();
            _dottori.setNome(dottori.getNome());
            _dottori.setCognome(dottori.getCognome());
            _dottori.setCf(dottori.getCf());
            _dottori.setColore(dottori.getColore());
            _dottori.setPassword(dottori.getPassword());
            dottoriRepository.save(_dottori);
        }
    }

    @Override
    public String getName(int id) {
        String a="";
        Optional<Dottori> optionalDottori=dottoriRepository.findById(id);
        if (optionalDottori.isPresent()){
            a+=optionalDottori.get().getNome()+" "+optionalDottori.get().getCognome();
            return a;
        }
        return null;
    }
}
