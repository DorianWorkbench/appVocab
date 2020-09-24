package net.peyrache.appvocab.modele;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import net.peyrache.appvocab.bdd.Sql;

import java.util.ArrayList;

public class Intero {
    private String intituleQuest;
    private String intituleRep;

    public Intero(String intituleQuest, String intituleRep){
        this.intituleQuest = intituleQuest;
        this.intituleRep = intituleRep;
    }

    public String getIntituleQuest(){
        return this.intituleQuest;
    }


    public String getIntituleRep(){
        return this.intituleRep;
    }

    public static ArrayList<Intero> listeDintero(String libelleQuest, Context context){

        ArrayList<Intero> listeIntero = new ArrayList<Intero>();
        Intero intero;
        Sql sql = new Sql(context);

        String strSql = "SELECT intituleQuest,intituleRep " +
                        "FROM QUESTIONREP, QUESTIONNAIRE " +
                        "WHERE QUESTIONREP.idQuest=QUESTIONNAIRE.id " +
                        "AND libelle='"+libelleQuest+"';";

        Cursor cursor = sql.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();

        if(cursor.moveToFirst()){
            do{
                intero = new Intero(cursor.getString(0), cursor.getString(1));
                listeIntero.add(intero);
            }while(cursor.moveToNext());
        }

        Integer nbListIntero = listeIntero.size();

        Log.d("nbListIntero",nbListIntero.toString());

        return listeIntero;
    }
    public static ArrayList<Intero> listeInteroWithId(Integer id,Context context){
        ArrayList<Intero> ListeIntero = new ArrayList<Intero>();
        Sql sql = new Sql(context);

        String strSql ="SELECT intituleQuest, intituleRep FROM QUESTIONREP WHERE idQuest="+id+";";
        Cursor cursor = sql.getReadableDatabase().rawQuery(strSql, null);

        if(cursor.moveToFirst()){
            do{

                Intero intero = new Intero(cursor.getString(0),cursor.getString(1));
                ListeIntero.add(intero);

            }while(cursor.moveToNext());
        }

        return ListeIntero;
    }

    public static Boolean verifIntero(Questionnaire questionnaire, Intero interoUser){
        Boolean verif = false;

        for(Intero uneIntero:questionnaire.getIntero()){
            if(interoUser.getIntituleQuest().equals(uneIntero.getIntituleQuest())){
                if(interoUser.getIntituleRep().equals(uneIntero.getIntituleRep())){
                    verif = true;
                    Log.d("repV",interoUser.intituleRep);
                    Log.d("repQ", uneIntero.getIntituleRep());
                }else{
                    verif = false;
                    Log.d("parici","par la");
                }
            }
        }

        return verif;
    }
}
