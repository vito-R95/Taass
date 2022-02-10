package com.example.AppointmedBackground.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name="Pazienti")
public class Pazienti {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="nome")
    private String nome;
    @Column(name="cognome")
    private String cognome;
    @Column(name="cf")
    private String cf;
    @Column(name="cell")
    private int cell;

    public Pazienti(int id,String nome, String cognome, String cf, int cell) {
        this.id=id;
        this.nome=nome;
        this.cognome=cognome;
        this.cf=cf;
        this.cell=cell;
    }
    public Pazienti(){}


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

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
