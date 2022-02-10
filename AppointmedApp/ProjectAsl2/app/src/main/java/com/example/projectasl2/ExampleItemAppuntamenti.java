package com.example.projectasl2;

public class ExampleItemAppuntamenti {
    private int image;
    private String line1;
    private String line2;
    private String line3;

    public ExampleItemAppuntamenti(int image2,String line12,String line22,String line32){
        image=image2;
        line1=line12;
        line2=line22;
        line3=line32;
    }

    public ExampleItemAppuntamenti(){

    }

    public int getImage(){
        return image;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }
}
