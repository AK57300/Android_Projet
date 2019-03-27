package com.example.td1.DAO;

import android.content.Context;
import android.util.Log;

import com.example.td1.ActiviteEnAttenteAvecResultat;
import com.example.td1.Modele.Periodicite;
import com.example.td1.RequeteSQL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

    public class PeriodiciteDAO implements DAO<Periodicite> {
        private final String URL_SERVER = "https://devbdd.iutmetz.univ-lorraine.fr/~kreutner1u/Android/";
        private ActiviteEnAttenteAvecResultat activite;
        private PeriodiciteDAO(ActiviteEnAttenteAvecResultat activite){
            this.activite = activite;
        }

        private Context context;
        private static PeriodiciteDAO instance;

        private PeriodiciteDAO (Context context){
            this.context=context;
        }

        public static PeriodiciteDAO getInstance (Context context){
            if (instance==null){
                instance = new PeriodiciteDAO(context);
            }
            return instance;
        }

        @Override
        public void findAll(){
            RequeteSQL req = new RequeteSQL(activite, this);
            req.execute(URL_SERVER + "login.php");
        }

        @Override
        public void insert(Periodicite objet) {
            RequeteSQL req = new RequeteSQL(activite, this);
            String params = "?nPeriodicite=" + objet.getnPeriodicite() + "&periodicity=" + objet.getPeriodicity();
            req.execute(URL_SERVER + "insertPeriodicite.php" + params);

        }

        @Override
        public void traiteFindAll(String result) {
            ArrayList<Periodicite> liste = new ArrayList<Periodicite>();
            try {
                JSONArray array = new JSONArray(result);
                for (int i = 0; i < array.length(); i++){
                    JSONObject row = array.getJSONObject(i);
                    Periodicite p = new Periodicite(row.getInt("nPeriodicite"), row.getString("periodicity"));
                    liste.add(p);
                }
                this.activite.notifyRetourRequeteFindAll(liste);
            } catch (JSONException e) {
                Log.i("pb json", String.valueOf(e));
            }

        }
    }


