package net.peyrache.appvocab.controller;

import android.content.Context;
import android.util.Log;

import net.peyrache.appvocab.modele.Resultat;

import java.util.ArrayList;

public class CResultat {

    private Context context;

    public CResultat(Context context){
        this.context = context;
    }

    /*public ArrayList<Resultat> listeResultat(){
        ArrayList<Resultat> listeResult = new ArrayList<Resultat>();
        listeResult = Resultat.listeResultat(this.context);
        return listeResult;
    }*/

    public void ajoutNote(Resultat resultat){
        resultat.ajoutNote();
    }

}
