package com.example.td1;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

public class RevueFragment extends Fragment implements ActiviteEnAttenteAvecResultat {
    @Override
    public void notifyRetourRequete(String result) {

    }

    @Override
    public void notifyRetourRequeteFindAll(ArrayList liste) {
      // this.liste.clear();
       // this.liste.addAll(liste);

       // ((BaseAdapter) this.listView.getAdapter()).notifyDataSetChanged();
       // this.terminePatience();
    }
}
