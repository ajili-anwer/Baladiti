package com.example.baladiti;

public class Module_signup {
    private String nom , prenom , mail , password , n_tel;

    public Module_signup(String nom, String prenom, String mail, String password, String n_tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.n_tel = n_tel;
    }

    public Module_signup( String az, String s, String az1, String s1) {
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getN_tel() {
        return n_tel;
    }
}
