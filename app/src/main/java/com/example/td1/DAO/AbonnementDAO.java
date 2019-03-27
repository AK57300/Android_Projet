package com.example.td1.DAO;

import android.content.Context;
import android.util.Log;

import com.example.td1.ActiviteEnAttenteAvecResultat;
import com.example.td1.Modele.Abonnement;
import com.example.td1.Modele.Periodicite;
import com.example.td1.Modele.Revue;
import com.example.td1.Modele.User;
import com.example.td1.RequeteSQL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AbonnementDAO implements DAO<Abonnement> {



    private final String URL_SERVER = "https://devbdd.iutmetz.univ-lorraine.fr/~kreutner1u/Android/";
    private ActiviteEnAttenteAvecResultat activite;
    private AbonnementDAO(ActiviteEnAttenteAvecResultat activite){
        this.activite = activite;
    }

    private Context context;
    private static AbonnementDAO instance;

    private AbonnementDAO (Context context){
        this.context=context;
    }

    public static AbonnementDAO getInstance (Context context){
        if (instance==null){
            instance = new AbonnementDAO(context);
        }
        return instance;
    }


    @Override
    public void findAll(){
        RequeteSQL req = new RequeteSQL(activite, this);
        req.execute(URL_SERVER + "abonnement.php");
    }

    @Override
    public void insert(Abonnement objet){
        RequeteSQL req = new RequeteSQL(activite, this);
        String params = "?id_user=" + objet.getId_user()+"&reference="+objet.getReference();
        req.execute(URL_SERVER + "insertAbonnement.php" + params);
    }


    public void update(Abonnement objet){
        RequeteSQL req = new RequeteSQL(activite, this);
        String params = "?id_user=" + objet.getId_user()+"&reference="+objet.getReference();
        req.execute(URL_SERVER + "updateAbonnement.php" + params);

    }


    public void delete(Abonnement objet){
        RequeteSQL req = new RequeteSQL(activite, this);
        String params = "?id_user=" + objet.getId_user()+"&reference="+objet.getReference();
        req.execute(URL_SERVER + "deleteAbonnement.php" + params);
    }

    @Override
    public void traiteFindAll(String result) {
        ArrayList<Abonnement> liste = new ArrayList<Abonnement>();
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++){
                JSONObject row = array.getJSONObject(i);
                Abonnement a = new Abonnement(new User(row.getInt("id_user"), row.getString("mail_user"), row.getString("pwd_user"), row.getString("type_user")), new Revue(row.getString("ref_revue"), row.getString("title_revue"), row.getString("desc_revue"), row.getDouble("fee_revue"), new Periodicite(row.getInt("id_periodicity"), row.getString("periode")), row.getString("url_img")));
                liste.add(a);
            }
            this.activite.notifyRetourRequeteFindAll(liste);
        } catch (JSONException e) {
            Log.i("pb json", String.valueOf(e));
        }

    }


    /*
    private Context context;
    private static RevueDAO instance;

    private RevueDAO (Context context){
        this.context=context;
    }

    public static RevueDAO getInstance (Context context){
        if (instance==null){
            instance = new RevueDAO(context);
        }
        return instance;
    }

    private static final String NOM_FICHIER = "name";

    public void Ecriture (ArrayList<Revue> revues){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(this.context.openFileOutput(NOM_FICHIER, context.MODE_PRIVATE));
            oos.writeObject(revues);
            oos.close();
        }
        catch (IOException ioe){

        }
    }

    public ArrayList<Revue> Lecture() {
        ArrayList<Revue> resultat = new ArrayList<>();
        try{
            ObjectInputStream ois = new ObjectInputStream(this.context.openFileInput(NOM_FICHIER));
            resultat = (ArrayList<Revue>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return resultat;
    }

    public ArrayList<Revue> findAll(){
        return Lecture();
    }

*/

}
