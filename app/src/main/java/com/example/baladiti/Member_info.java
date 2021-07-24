package com.example.baladiti;

public class Member_info {
    private String nom;
    private String email_info;
    private String tel;
    private String fax;
    private String addresse;
    private String type;

    private String genre;

    public Member_info(){

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail_info() {
        return email_info;
    }

    public void setEmail_info(String email_info) {
        this.email_info = email_info;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddresse() {
        return addresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;



    }
}
