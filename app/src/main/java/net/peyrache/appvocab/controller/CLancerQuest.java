package net.peyrache.appvocab.controller;

import android.content.Context;

import net.peyrache.appvocab.modele.Intero;
import net.peyrache.appvocab.modele.Questionnaire;

public class CLancerQuest {

    private Context context;

    public CLancerQuest(Context context){
        this.context = context;
    }

    public Questionnaire getQuestionnaire(String libelle){
        return Questionnaire.getQuestionnaire(libelle,this.context);
    }
    public Intero creationIntero(String quest, String rep){
        Intero intero = new Intero(quest,rep);
        return intero;
    }
    public Boolean verifIntero(Questionnaire questionnaire, Intero interoUser){
        return Intero.verifIntero(questionnaire,interoUser);
    }

}
