package com.example.AppointmedBackground.Services;

import com.example.AppointmedBackground.Models.Pazienti;
import com.example.AppointmedBackground.Repository.PazientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("pazientiService")
public class PazientiServiceImpl implements PazientiService{
    @Autowired
    private PazientiRepository pazientiRepository;

    public PazientiServiceImpl(){
        System.out.println("Service Pazienti is created");
    }

    @Override
    public void add(Pazienti pazienti) {
        pazientiRepository.save(pazienti);
    }

    @Override
    public List<Pazienti> getPazienti() {
        return pazientiRepository.findAll();
    }

    @Override
    public Pazienti getPaziente(int id) {
        Optional<Pazienti> optionalPazienti=pazientiRepository.findById(id);
        if (optionalPazienti.isPresent()){
            return optionalPazienti.get();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        pazientiRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        pazientiRepository.deleteAll();
    }

    @Override
    public void update(int id, Pazienti pazienti) {
        Optional<Pazienti> optionalPazienti=pazientiRepository.findById(id);
        if (optionalPazienti.isPresent()){
            Pazienti _pazienti= optionalPazienti.get();
            _pazienti.setNome(pazienti.getNome());
            _pazienti.setCognome(pazienti.getCognome());
            _pazienti.setCell(pazienti.getCell());
            _pazienti.setCf(pazienti.getCf());
            pazientiRepository.save(_pazienti);
        }
    }

    @Override
    public String getName(int id) {
        String a="";
        Optional<Pazienti> optionalPazienti=pazientiRepository.findById(id);
        if (optionalPazienti.isPresent()){
            a+=optionalPazienti.get().getNome()+" "+optionalPazienti.get().getCognome();
            return a;
        }
        return null;
    }
}
