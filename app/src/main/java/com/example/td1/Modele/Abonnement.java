package com.example.td1.Modele;

public class Abonnement {
    public User id_user;
    public Revue reference;

    public Abonnement(User id_user, Revue reference) {
        this.id_user = id_user;
        this.reference = reference;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public Revue getReference() {
        return reference;
    }

    public void setReference(Revue reference) {
        this.reference = reference;
    }
}
