package com.example.baladiti;

public class Module_rec_nv {

    private String sujet , spinner,textLatLang , textAdress , textDate ;//

//
    public String getTextDate() {
        return textDate;
    }
//
    public void setTextDate(String textDate) {
        this.textDate = textDate;
    }
//
    public Module_rec_nv(String sujet, String spinner , String textLatlang , String textAdress , String textDate ) {
        this.sujet = sujet;
        this.spinner = spinner;
        this.textAdress = textAdress;
        this.textLatLang = textLatlang;
        this.textDate = textDate;//
    }


    public Module_rec_nv() {
    }


    public void setSujet(String sujet) {
        this.sujet = sujet;
    }



    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }

    public void setTextLatLang(String textLatLang) {
        this.textLatLang = textLatLang;
    }

    public void setTextAdress(String textAdress) {
        this.textAdress = textAdress;
    }

    public  String getSujet() {
        return sujet;
    }



    public String getSpinner() {
        return spinner;
    }

    public String getTextLatLang() {
        return textLatLang;
    }

    public String getTextAdress() {return textAdress;}




}
