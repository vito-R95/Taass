package com.example.AppointmedBackground.Controllers;


import com.example.AppointmedBackground.Models.Messaggi;
import com.example.AppointmedBackground.Services.MessaggiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/messaggi")
public class MessaggiController {

    @Autowired
    private MessaggiService messaggiService;

    //INSERISCE UN MESSAGGIO
    @RequestMapping(value ="/addMessaggio",method= RequestMethod.POST)
    public void add(@RequestBody Messaggi messaggi) {
        messaggiService.add(messaggi);
    }

    //RITORNA TUTTI I MESSAGGI
    @GetMapping("/getMessaggi")
    public List<Messaggi> getMessaggi() {
        return messaggiService.getMessaggi();
    }

    //RITORNA UN DETERMINATO MESSAGGIO
    @GetMapping("/getMessaggio/{id}")
    public Messaggi getMessaggio(@PathVariable int id) {
        return messaggiService.getMessaggio(id);
    }

    //ELIMINA UN MESSAGGIO
    @RequestMapping(value ="/deleteMessaggio/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        messaggiService.delete(id);
    }
    //ELIMINA TUTTI I MESSAGGI
    @RequestMapping(value= "/deleteAll", method= RequestMethod.DELETE)
    public void deleteAll() {
        messaggiService.deleteAll();

    }
}
