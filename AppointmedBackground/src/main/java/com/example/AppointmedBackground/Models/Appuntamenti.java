package com.example.AppointmedBackground.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name="Appuntamenti")
public class Appuntamenti {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "giorno")
    private LocalDate giorno;
    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "ora")
    private LocalTime ora;
    @Column(name = "TipoAppuntamento")
    private String TipoAppuntamento;
    @Column(name = "idPaziente")
    private int idPaziente;
    @Column(name = "idDottore")
    private int idDottore;

    public Appuntamenti(int id,LocalDate giorno,LocalTime ora,int idPaziente,int idDottore,String TipoAppuntamento){
        this.id=id;
        this.giorno=giorno;
        this.ora=ora;
        this.idPaziente=idPaziente;
        this.idDottore=idDottore;
        this.TipoAppuntamento=TipoAppuntamento;
    }

    public Appuntamenti(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getGiorno() {
        return giorno;
    }

    public void setGiorno(LocalDate giorno) {
        this.giorno = giorno;
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public String getTipoAppuntamento() {
        return TipoAppuntamento;
    }

    public void setTipoAppuntamento(String tipoAppuntamento) {
        TipoAppuntamento = tipoAppuntamento;
    }

    public int getIdPaziente() {
        return idPaziente;
    }

    public void setIdPaziente(int idPaziente) {
        this.idPaziente = idPaziente;
    }

    public int getIdDottore() {
        return idDottore;
    }

    public void setIdDottore(int idDottore) {
        this.idDottore = idDottore;
    }
}
