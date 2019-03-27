package com.example.td1.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.td1.DAO.RevueDAO;
import com.example.td1.ImageFromURL;
import com.example.td1.Modele.ListeRevueAdapteur;
import com.example.td1.Modele.Revue;
import com.example.td1.R;

import java.util.ArrayList;

public class ListeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ArrayList<Revue> listeRevues = new ArrayList<Revue>();
    private ArrayList<Bitmap>listeImages;
    private ListeRevueAdapteur adapteur;
    private TextView welcome;
    private ListView listView;
    private String utilisateur;
    private RevueDAO rDAO = RevueDAO.getInstance(this);
    private int indiceMAJ;
    public static final int APPEL_NOUVELLE=1;
    public static final int APPEL_MAJ=2;
    public static final int ANNULER=3;
    private Revue revue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        rDAO.findAll();
        this.listeImages = new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            this.utilisateur = savedInstanceState.getString("mail");
            this.listeRevues = (ArrayList<Revue> )savedInstanceState.getSerializable("liste");
            this.indiceMAJ = savedInstanceState.getInt("indice");
        }
        else{
            this.utilisateur = this.getIntent().getStringExtra("mail");
            Log.i("texte", this.utilisateur + "Chaine vide");
            this.indiceMAJ = -1;
        }

        for (int i = 0; i < this.listeRevues.size(); i++){
            this.listeImages.add(null);
            this.chargeImage(i);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        this.listView = this.findViewById(R.id.la_liste);
        this.listView.setOnItemClickListener(this);
        this.listView.setOnItemLongClickListener(this);

        this.adapteur = new ListeRevueAdapteur(this, this.listeRevues, this.listeImages);
        this.listView.setAdapter(this.adapteur);

        this.welcome = (TextView)this.findViewById(R.id.la_bienvenue);
        welcome.setText(String.format(getResources().getString(R.string.WelcomeMessage), this.utilisateur));

    }



    protected void onPause(){
        super.onPause();
        Log.i("cycle","onPause");
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("mail",this.utilisateur);
        outState.putSerializable("liste", this.listeRevues);
        outState.putInt("indice", this.indiceMAJ);
    }

    protected void onStop() {
        super.onStop();
        Log.i("cycle", "onStop");
    }

    public void onClickNouvelleRevue(View view){
        Intent intent = new Intent(ListeActivity.this, SaisieActvity.class);
        startActivityForResult(intent, APPEL_NOUVELLE);
    }

    public void onClickReturn(View view){
        this.setResult(ANNULER);
        this.finish();
    }

    @Override
    public void onBackPressed(){
        this.onClickReturn(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ListeActivity.this, SaisieActvity.class);
        intent.putExtra("revue",listeRevues.get(position));
        indiceMAJ = position;
        startActivityForResult(intent, APPEL_MAJ);
    }

    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data){

        if (resultcode==SaisieActvity.ACTION_ANNULER){
            return;
        }

        if (requestcode==APPEL_NOUVELLE && resultcode==SaisieActvity.ACTION_VALIDEE) {
            //listeRevues.add((Revue) data.getSerializableExtra("revue"));
            rDAO.insert((Revue) data.getSerializableExtra("revue"));
            this.listeImages.add(null);
            chargeImage(this.listeImages.size()-1);
        } else{
          //  listeRevues.set(indiceMAJ,(Revue) data.getSerializableExtra("revue"));
            rDAO.update((Revue) data.getSerializableExtra("revue"));
            chargeImage(indiceMAJ);

        }
        rDAO.findAll();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Toast.makeText(ListeActivity.this, "Suppression", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder adb = new AlertDialog.Builder(ListeActivity.this);
        adb.setTitle("Delete?");
        adb.setMessage("Are you sure you want to delete " + position);
        adb.setNegativeButton("Cancel", null);
        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                listeRevues.remove(position);

            }});
        adb.show();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id==android.R.id.home){
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void chargeImage(int idx){
        ImageFromURL ifu = new ImageFromURL(this);
        ifu.execute("https://devweb.iutmetz.univ-lorraine.fr/~laroche5/devmob_unes/"+ this.listeRevues.get(idx).getVisuel(),String.valueOf(idx));
        Log.i("icone","lien charge");
    }

    public void receptionneImage(Object[] result){
        listeImages.set(Integer.parseInt(result[1].toString()), (Bitmap)result[0]);
        this.adapteur.notifyDataSetChanged();
        Log.i("icone","lien receptionne");
    }

}