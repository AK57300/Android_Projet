package com.example.td1.Modele;

public class Categorie {
    public int id_categorie;
    public String nom;
    public String visuel;

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVisuel() {
        return visuel;
    }

    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }

    public Categorie(int id_categorie, String nom, String visuel) {
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.visuel = visuel;
    }
}
