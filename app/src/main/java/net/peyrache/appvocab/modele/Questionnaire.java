package net.peyrache.appvocab.modele;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import net.peyrache.appvocab.bdd.Sql;

import java.util.ArrayList;

public class Questionnaire {
    private ArrayList<Intero> intero;
    private String libelle;
    private Sql sql;
    private Context context;

    public Questionnaire(String libelle, ArrayList<Intero> intero, Context context){
        this.libelle = libelle;
        this.intero = intero;
        this.context = context;
    }

    public String getLibelle(){
        return this.libelle;
    }

    public ArrayList<Intero> getIntero(){
        return intero;
    }

    public void ajoutQuestionnaire(){
        this.sql = new Sql(this.context);

        String strSql = "INSERT INTO QUESTIONNAIRE(libelle) VALUES('"+this.libelle+"')";
        sql.getWritableDatabase().execSQL(strSql);

        for(int i = 0; i<intero.size(); i++){

            strSql= "INSERT INTO QUESTIONREP(intituleQuest,intituleRep,idQuest) VALUES('"+intero.get(i).getIntituleQuest()+"','"+intero.get(i).getIntituleRep()+"',"+recupId()+");";

            try{
                sql.getWritableDatabase().execSQL(strSql);
            }catch(Exception e){
                Log.d("erreur", "erreur sql");
            }
        }
        this.sql.close();
    }

    public static Boolean questiExist(String libelle, Context context){
        libelle = libelle.replace("'","''");
        Boolean exist = false;
        Sql sql = new Sql(context);

        String strSql = "SELECT * FROM QUESTIONNAIRE WHERE libelle = '"+libelle+"'";

        Cursor cursor = sql.getReadableDatabase().rawQuery(strSql,null);
        cursor.moveToFirst();

        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(1).isEmpty()){
                    Log.d("idQuestionnaire", "Le questionnaire n'existe pas");
                    exist=false;
                }else{
                    exist = true;
                }
            }while(cursor.moveToNext());
        }
        sql.close();
        return exist;
    }
    public Integer recupId(){
        Integer id=0;

        this.sql = new Sql(this.context);

        String strSql = "SELECT id FROM QUESTIONNAIRE WHERE libelle='"+this.libelle+"';";

        Cursor cursor = sql.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();

        if(cursor.moveToFirst()){
            do{
              id = cursor.getInt(0);
              Log.d("id", id.toString());
            }while(cursor.moveToNext());
        }
        return id;
    }


    public static ArrayList<Questionnaire> listeQuestionnaire(Context context){

        ArrayList<Questionnaire> questionnaireListe = new ArrayList<Questionnaire>();
        ArrayList<String> nomQuest = Questionnaire.ListeNomQuestionnaire(context);
        ArrayList<Intero> listeIntero;
        Questionnaire unQuestionnaire;

        //pour chaque libelle, il me faut les interos associées.

        for(String nomQuestionnaire:nomQuest){
            listeIntero = Intero.listeDintero(nomQuestionnaire,context);
            unQuestionnaire = new Questionnaire(nomQuestionnaire,listeIntero,context);
            questionnaireListe.add(unQuestionnaire);
        }

        return questionnaireListe;
    }
    public static Questionnaire getQuestionnaire(String libelle, Context context){
        ArrayList<Intero> getIntero = Intero.listeDintero(libelle,context);
        Questionnaire questionnaire = new Questionnaire(libelle,getIntero,context);

        Log.d("questCrea", "Creation de l'objet");
        return questionnaire;
    }
    public static String recupLibelleQuest(Context context, Integer id){
        String libelleQuest = "";

        String strSql = "SELECT libelle FROM QUESTIONNAIRE WHERE id="+id+";";
        Sql sql = new Sql(context);

        Cursor cursor = sql.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();

        if(cursor.moveToFirst()){
            do{
                libelleQuest = cursor.getString(0);
            }while(cursor.moveToNext());
        }
        return libelleQuest;
    }
    public static ArrayList<String> ListeNomQuestionnaire(Context context){

        ArrayList<String> listeNomQuestionnaire = new ArrayList<String>();
        String nomQuest;
        Sql sql = new Sql(context);

        String strSql = "SELECT libelle FROM QUESTIONNAIRE";
        Cursor cursor = sql.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();

        if(cursor.moveToFirst()){
            do{
                nomQuest = cursor.getString(0);
                listeNomQuestionnaire.add(nomQuest);
            }while(cursor.moveToNext());
        }

       /* Integer nbNomList = listeNomQuestionnaire.size();
        Log.d("nbNomList",nbNomList.toString());*/

        return listeNomQuestionnaire;
    }
    //Récupération des questionnaires lorsqu'il existe au moins 1 résultat enregistré.

    /*
    public static ArrayList<Questionnaire> questionnaireResult(Context context){
        ArrayList<Questionnaire> listResultQuest = new ArrayList<Questionnaire>();
        Sql sql = new Sql(context);
        String strSql = "SELECT";
        Cursor cursor = sql.getReadableDatabase().rawQuery(strSql,null);

        return listResultQuest;
    }*/
}