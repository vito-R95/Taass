package com.example.projectasl2;

public class ExampleItemDottori {
    private int imageDoc;
    private String nomeDoc;
    private String colore;

    public ExampleItemDottori(int imageD,String nomeD) {
        imageDoc = imageD;
        nomeDoc = nomeD;
    }
    public ExampleItemDottori(){

    }

    public ExampleItemDottori(int imageD, String nomeD, String color) {
        imageDoc = imageD;
        nomeDoc = nomeD;
        colore=color;
    }

    public int getImage(){
        return imageDoc;
    }

    public String getLine1() {
        return nomeDoc;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }
}
