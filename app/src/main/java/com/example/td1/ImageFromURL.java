package com.example.td1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.td1.Activity.ListeActivity;

import java.io.InputStream;
import java.util.BitSet;

public class ImageFromURL extends AsyncTask<String, Void, Object[]> implements ActiviteEnAttenteImage {
    private ListeActivity activite;

    public ImageFromURL(ListeActivity listeActivity) {
        this.activite = listeActivity;
    }

    @Override
    protected Object[] doInBackground(String... urlEtIndice) {

        String urlIncone = urlEtIndice[0];
        Bitmap icone = null;
        try {
            InputStream in = new java.net.URL(urlIncone).openStream();
            icone = BitmapFactory.decodeStream(in);
            in.close();
        } catch (Exception e) {
            Log.i("Pas d'icone", urlIncone + " utilisez un drawable générique à la place !");
        }
        return new Object[]{icone, urlEtIndice[1]};
    }

    @Override
    protected void onPostExecute(Object[] result) {
        this.activite.receptionneImage(result);

    }
}