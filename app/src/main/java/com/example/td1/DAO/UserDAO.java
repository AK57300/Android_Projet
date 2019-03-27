package com.example.td1.DAO;

import android.content.Context;
import android.util.Log;

import com.example.td1.ActiviteEnAttenteAvecResultat;
import com.example.td1.Modele.User;
import com.example.td1.RequeteSQL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserDAO implements DAO<User> {
    private final String URL_SERVER = "https://devbdd.iutmetz.univ-lorraine.fr/~kreutner1u/Android/";
    private ActiviteEnAttenteAvecResultat activite;
    private UserDAO(ActiviteEnAttenteAvecResultat activite){
        this.activite = activite;
    }

    private Context context;
    private static UserDAO instance;

    private UserDAO (Context context){
        this.context=context;
    }

    public static UserDAO getInstance (Context context){
        if (instance==null){
            instance = new UserDAO(context);
        }
        return instance;
    }

    @Override
    public void findAll(){
        RequeteSQL req = new RequeteSQL(activite, this);
        req.execute(URL_SERVER + "login.php");
    }

    @Override
    public void insert(User objet){
        RequeteSQL req = new RequeteSQL(activite, this);
        String params = "?mail_user=" + objet.getMail()+"&pwd_user="+objet.getPassword()+"&type_user="+objet.getUserType();
        req.execute(URL_SERVER + "insertRegister.php" + params);

    }



    @Override
    public void traiteFindAll(String result) {
        ArrayList<User> liste = new ArrayList<User>();
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++){
                JSONObject row = array.getJSONObject(i);
                User u = new User(row.getInt("id_user"), row.getString("mail_user"), row.getString("pwd_user"), row.getString("type_user"));
                liste.add(u);
            }
            this.activite.notifyRetourRequeteFindAll(liste);
        } catch (JSONException e) {
            Log.i("pb json", String.valueOf(e));
        }

    }
}
