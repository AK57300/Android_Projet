package com.example.td1.Modele;

import java.io.Serializable;

public class Revue implements Serializable {
    private String reference;
    private String title;
    private String description;
    private double fee;
    private Periodicite periodicite;
    private String visuel;

    public Revue() {

    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Periodicite getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(Periodicite periodicite) {
        this.periodicite = periodicite;
    }

    public String getVisuel() {
        return visuel;
    }

    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }


    public Revue(String reference, String title, String description, double fee, Periodicite periodicite, String visuel) {
        this.reference = reference;
        this.title = title;
        this.description = description;
        this.fee = fee;
        this.periodicite = periodicite;
        this.visuel = visuel;
    }

    @Override
    public String toString() {
        return title + " (" +  fee +" euros)";
    }


}