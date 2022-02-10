package com.example.AppointmedBackground.Controllers;


import com.example.AppointmedBackground.Models.Pazienti;
import com.example.AppointmedBackground.Services.PazientiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/pazienti")
public class PazientiController {

    @Autowired
    private PazientiService pazientiService;

    //RITORNA TUTTI I PAZIENTI
    @GetMapping("/getPazienti")
    public List<Pazienti> getList(){
        return pazientiService.getPazienti();
    }

    //INSERISCE I PAZIENTI
    @RequestMapping(value = "/addPaziente", method = RequestMethod.POST)
    public void addPaziente(@RequestBody Pazienti pazienti){
        pazientiService.add(pazienti);
    }

    //RITORNA UN PAZIENTE
    @GetMapping("/getPaziente/{id}")
    public Pazienti getPaziente(@PathVariable int id){
        return pazientiService.getPaziente(id);
    }

    //ELIMINA UN PAZIENTE ATTRAVERSO ID
    @RequestMapping(value = "/deletePaziente/{id}", method = RequestMethod.DELETE)
    public void deletePaziente(@PathVariable int id){
        pazientiService.delete(id);
    }

    //ELIMINA TUTTI
    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public void deleteAll(){
        pazientiService.deleteAll();
    }

    //UPDATE
    @RequestMapping(value = "/updatePaziente/{id}", method = RequestMethod.PUT)
    public void updatePaziente(@PathVariable int id,@RequestBody Pazienti pazienti){
        pazientiService.update(id,pazienti);
    }
    //RITORNA IL NOME E COGNOME DI UN PAZIENTE
    @GetMapping("/getNome/{id}")
    public String getNome(@PathVariable int id){
        return pazientiService.getName(id);
    }


}
