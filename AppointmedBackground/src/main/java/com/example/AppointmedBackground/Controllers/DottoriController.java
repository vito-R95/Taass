package com.example.AppointmedBackground.Controllers;


import com.example.AppointmedBackground.Models.Dottori;
import com.example.AppointmedBackground.Services.DottoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/dottori")
public class DottoriController {

    @Autowired
    private DottoriService dottoriService;

    //RITORNA TUTTI I DOTTORI
    @GetMapping("/getDottori")
    public List<Dottori> getDottori(){
        return dottoriService.getDottori();
    }

    //RITORNA UN DOTTORE TRAMITE ID
    @GetMapping("/getDottore/{id}")
    public Dottori getDottore(@PathVariable int id){
        return dottoriService.getDottore(id);
    }

    //INSERISCE I PAZIENTI
    @RequestMapping(value = "/addDottore", method = RequestMethod.POST)
    public void addDottore(@RequestBody Dottori dottori){
        dottoriService.addDottore(dottori);
    }

    //ELIMINA UN PAZIENTE ATTRAVERSO ID
    @RequestMapping(value = "/deleteDottore/{id}", method = RequestMethod.DELETE)
    public void deletePaziente(@PathVariable int id){
        dottoriService.delete(id);
    }

    //ELIMINA TUTTI
    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public void deleteAll(){
        dottoriService.deleteAll();
    }

    //UPDATE
    @RequestMapping(value = "/updateDottore/{id}", method = RequestMethod.PUT)
    public void updatePaziente(@PathVariable int id,@RequestBody Dottori dottori){
        dottoriService.update(id,dottori);

    }

    //RITORNA IL NOME E COGNOME DEL DOTTORE ATTRAVERSO L'ID
    @GetMapping(value = "/getNome/{id}")
    public String getNome(@PathVariable int id){
        return dottoriService.getName(id);
    }


}
