package com.example.AppointmedBackground.Controllers;

import com.example.AppointmedBackground.Models.Appuntamenti;
import com.example.AppointmedBackground.Services.AppuntamentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/appuntamenti")
public class AppuntamentiController {
    @Autowired
    private AppuntamentiService appuntamentiService;

    //RITORNA TUTTI GLI APPUNTAMENTI
    @GetMapping(value = "/getAppuntamenti")
    public List<Appuntamenti> getAppuntamenti(){
        return appuntamentiService.getAppuntamenti();
    }
    //RITORNA UN APPUNTAMENTO TRAMITE ID
    @GetMapping(value = "/getAppuntamento/{id}")
    public Appuntamenti getAppuntamenti(@PathVariable int id){
        return appuntamentiService.getAppuntamento(id);
    }
    //AGGIUNGI APPUNTAMENTO
    @RequestMapping(value = "/addAppuntamento", method = RequestMethod.POST)
    public void addAppuntamento(@RequestBody Appuntamenti appuntamenti){
        appuntamentiService.addAppuntamenti(appuntamenti);
    }
    //ELIMINA APPUNTAMENTO
    @RequestMapping(value = "/deleteAppuntamento/{id}", method = RequestMethod.DELETE)
    public void addAppuntamento(@PathVariable int id){
        appuntamentiService.delete(id);
    }
    //ELIMINA TUTTO
    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public void deleteAll(){
        appuntamentiService.deleteAll();
    }
    //MODIFICA APPUNTAMENTO
    @RequestMapping(value = "/updateAppuntamenti/{id}", method = RequestMethod.PUT)
    public void updateAppuntamenti(@PathVariable int id,@RequestBody Appuntamenti appuntamenti){
        appuntamentiService.update(id,appuntamenti);
    }
    //RITORNA UNA STRINGA
    @GetMapping(value = "/getApp/{id}")
    public String getApp(@PathVariable int id){
        return appuntamentiService.app(id);
    }


}
