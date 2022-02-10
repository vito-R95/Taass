package com.example.AppointmedBackground.Controllers;

import com.example.AppointmedBackground.Models.PrimaVisita;
import com.example.AppointmedBackground.Services.PrimaVisitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/primavisita")
public class PrimaVisitaController {
    @Autowired
    private PrimaVisitaService primaVisitaService;

    //RITORNA TUTTI GLI APPUNTAMENTI
    @GetMapping(value = "/getPrimavisita")
    public List<PrimaVisita> getPrimaVisita(){
        return primaVisitaService.getPrimaVisita();
    }

    //RITORNA UN APPUNTAMENTO TRAMITE ID
    @GetMapping(value = "/getPV/{id}")
    public PrimaVisita getPV(@PathVariable int id){

        return primaVisitaService.getPV(id);
    }
    //AGGIUNGI APPUNTAMENTO
    @RequestMapping(value = "/addPrimavisita", method = RequestMethod.POST)
    public void addPrimaVisita(@RequestBody PrimaVisita primaVisita){
        primaVisitaService.addPrimaVisita(primaVisita);
    }

    //ELIMINA APPUNTAMENTO
    @RequestMapping(value = "/deletePrimavisita/{id}", method = RequestMethod.DELETE)
    public void deletePrimavisita(@PathVariable int id){
        primaVisitaService.delete(id);
    }
    //ELIMINA TUTTO
    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public void deleteAll(){
        primaVisitaService.deleteAll();
    }
    //MODIFICA APPUNTAMENTO
    @RequestMapping(value = "/updatePrimavisita/{id}", method = RequestMethod.PUT)
    public void updatePrimavisita(@PathVariable int id,@RequestBody PrimaVisita primavisita){
        primaVisitaService.update(id,primavisita);
    }
}
