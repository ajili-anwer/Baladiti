package com.example.baladiti;

public class Member_user {
    private String nom ;
    private String prenom;
    private String mail ;
    private String n_tel;
    private String password ;

    public Member_user(){}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getN_tel() {
        return n_tel;
    }

    public void setN_tel(String n_tel) {
        this.n_tel = n_tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
