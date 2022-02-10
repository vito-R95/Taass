package com.example.AppointmedBackground.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name="PrimaVisita")
public class PrimaVisita {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "giorno")
    private LocalDate giorno;
    @JsonFormat(pattern = "hh:mm:ss a")
    @Column(name = "ora")
    private LocalTime ora;
    @Column(name = "TipoAppuntamento")
    private String TipoAppuntamento;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cognome")
    private String cognome;
    @Column(name = "idDottore")
    private int idDottore;

    public PrimaVisita(int id,LocalDate giorno,LocalTime ora,String nome,String cognome,int idDottore,String TipoAppuntamento){
        this.id=id;
        this.giorno=giorno;
        this.ora=ora;
        this.nome=nome;
        this.cognome=cognome;
        this.idDottore=idDottore;
        this.TipoAppuntamento=TipoAppuntamento;
    }

    public PrimaVisita(){

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getIdDottore() {
        return idDottore;
    }

    public void setIdDottore(int idDottore) {
        this.idDottore = idDottore;
    }
}
