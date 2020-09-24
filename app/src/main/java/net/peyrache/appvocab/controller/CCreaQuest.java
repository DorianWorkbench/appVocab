package net.peyrache.appvocab.controller;

import android.content.Context;

import net.peyrache.appvocab.modele.Intero;
import net.peyrache.appvocab.modele.Questionnaire;

import java.util.ArrayList;

public class CCreaQuest {

    private Context context;
    private Questionnaire questi;

    public CCreaQuest(Context context){
        this.context = context;
    }

    public void creationQuestionnaire(String libelle, ArrayList<Intero> intero){
        this.questi = new Questionnaire(libelle, intero, this.context);
        questi.ajoutQuestionnaire();
    }
    public Boolean questiExist(String libelle, Context context){
        return Questionnaire.questiExist(libelle, context);
    }
}
