package com.example.AppointmedBackground.Services;

import com.example.AppointmedBackground.Models.Dottori;
import com.example.AppointmedBackground.Models.PrimaVisita;
import com.example.AppointmedBackground.Repository.DottoriRepository;
import com.example.AppointmedBackground.Repository.PrimaVisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("primaVisitaService")
public class PrimaVisitaServiceImpl implements PrimaVisitaService{
    @Autowired
    private PrimaVisitaRepository primaVisitaRepository;
    @Autowired
    private DottoriRepository dottoriRepository;

    public PrimaVisitaServiceImpl(){
        System.out.println("Service prima visita is created");
    }

    @Override
    public List<PrimaVisita> getPrimaVisita() {
        return primaVisitaRepository.findAll();
    }

    @Override
    public void addPrimaVisita(PrimaVisita primaVisita) {
        primaVisitaRepository.save(primaVisita);
    }

    @Override
    public PrimaVisita getPV(int id) {
        Optional<PrimaVisita> optionalPrimaVisita=primaVisitaRepository.findById(id);
        if (optionalPrimaVisita.isPresent()){
            return optionalPrimaVisita.get();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        primaVisitaRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        primaVisitaRepository.deleteAll();
    }

    @Override
    public void update(int id, PrimaVisita primaVisita) {
        Optional<PrimaVisita> optionalPrimaVisita=primaVisitaRepository.findById(id);
        if (optionalPrimaVisita.isPresent()){
            PrimaVisita _primaVisita = optionalPrimaVisita.get();
            _primaVisita.setTipoAppuntamento(primaVisita.getTipoAppuntamento());
            _primaVisita.setGiorno(primaVisita.getGiorno());
            _primaVisita.setOra(primaVisita.getOra());
            _primaVisita.setIdDottore(primaVisita.getIdDottore());
            _primaVisita.setNome(primaVisita.getNome());
            _primaVisita.setCognome(primaVisita.getCognome());
            primaVisitaRepository.save(_primaVisita);
        }
    }

    @Override
    public String app(int id) {
        return null;
    }

    @Override
    public String getDottore(int id) {
        String a="";
        Optional<Dottori> optionalDottori=dottoriRepository.findById(id);
        if (optionalDottori.isPresent()){
            a+=optionalDottori.get().getNome()+" "+optionalDottori.get().getCognome();
            return a;
        }
        return null;
    }
}
