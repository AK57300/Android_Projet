package com.example.td1;

import com.example.td1.Modele.Categorie;

import java.util.ArrayList;

public interface ActiviteEnAttenteAvecResultat extends ActiviteEnAttente{
    public void notifyRetourRequete(String result);
    public void notifyRetourRequeteFindAll(ArrayList liste);
}
