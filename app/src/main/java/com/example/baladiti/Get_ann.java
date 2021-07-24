package com.example.baladiti;

public class Get_ann {

    private String activite;
    private String slug;
    private String adresse;

    private String image;


    public Get_ann(String slug , String adresse , String activite , String image ){
        this.slug = slug;
        this.adresse = adresse;
        this.activite = activite;
        this.image = image;
    }

    public Get_ann() {

    }


    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
