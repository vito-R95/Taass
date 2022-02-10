package com.example.AppointmedBackground.Services;

import com.example.AppointmedBackground.Models.Appuntamenti;
import com.example.AppointmedBackground.Models.Dottori;
import com.example.AppointmedBackground.Models.Pazienti;
import com.example.AppointmedBackground.Repository.AppuntamentiRepository;
import com.example.AppointmedBackground.Repository.DottoriRepository;
import com.example.AppointmedBackground.Repository.PazientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("appuntamentiService")
public class AppuntamentiServiceImpl implements AppuntamentiService{
    @Autowired
    private AppuntamentiRepository appuntamentiRepository;
    @Autowired
    private DottoriRepository dottoriRepository;
    @Autowired
    private PazientiRepository pazientiRepository;

    public AppuntamentiServiceImpl(){
        System.out.println("Service appuntamenti is created");
    }

    @Override
    public List<Appuntamenti> getAppuntamenti() {
        return appuntamentiRepository.findAll();
    }

    @Override
    public void addAppuntamenti(Appuntamenti appuntamenti) {
        appuntamentiRepository.save(appuntamenti);
    }

    @Override
    public Appuntamenti getAppuntamento(int id) {
        Optional<Appuntamenti> optionalAppuntamenti=appuntamentiRepository.findById(id);
        if (optionalAppuntamenti.isPresent()){
            return optionalAppuntamenti.get();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        appuntamentiRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        appuntamentiRepository.deleteAll();
    }

    @Override
    public void update(int id, Appuntamenti appuntamenti) {
        Optional<Appuntamenti> optionalAppuntamenti=appuntamentiRepository.findById(id);
        if (optionalAppuntamenti.isPresent()){
            Appuntamenti _appuntamenti = optionalAppuntamenti.get();
            _appuntamenti.setTipoAppuntamento(appuntamenti.getTipoAppuntamento());
            _appuntamenti.setGiorno(appuntamenti.getGiorno());
            _appuntamenti.setOra(appuntamenti.getOra());
            _appuntamenti.setIdDottore(appuntamenti.getIdDottore());
            _appuntamenti.setIdPaziente(appuntamenti.getIdPaziente());
            appuntamentiRepository.save(_appuntamenti);
        }
    }

    @Override
    public String app(int id) {
        String a="";
        Optional<Appuntamenti> optionalAppuntamenti=appuntamentiRepository.findById(id);
        if (optionalAppuntamenti.isPresent()){
            a+=optionalAppuntamenti.get().getTipoAppuntamento()+" "+optionalAppuntamenti.get().getGiorno()+" "+optionalAppuntamenti.get().getOra()+" DOTTORE:"
                    +optionalAppuntamenti.get().getIdDottore()+" "+getDottore(optionalAppuntamenti.get().getIdDottore())+" PAZIENTE:"+optionalAppuntamenti.get().getIdPaziente()+" "+ getPaziente(optionalAppuntamenti.get().getIdPaziente()) ;
            return a;
        }
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

    @Override
    public String getPaziente(int id) {
        String a="";
        Optional<Pazienti> optionalPaziente=pazientiRepository.findById(id);
        if (optionalPaziente.isPresent()){
            a+=optionalPaziente.get().getNome()+" "+optionalPaziente.get().getCognome();
            return a;
        }
        return null;
    }
}
