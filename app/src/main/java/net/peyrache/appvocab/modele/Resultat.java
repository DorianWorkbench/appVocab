package net.peyrache.appvocab.modele;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import net.peyrache.appvocab.bdd.Sql;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class Resultat {

    private String note;
    public Questionnaire questionnaire;
    private String date;
    private Context context;
    private Sql sql;

    public Resultat(String note, String date,Questionnaire questionnaire, Context context){
        this.note = note;
        this.questionnaire = questionnaire;
        this.date = date;
        this.context = context;
    }

    public String getNote(){
        return this.note;
    }public String getDate(){
        return this.date;
    }
    public void ajoutNote() {
        Questionnaire questionnaire = this.questionnaire;
        this.sql = new Sql(this.context);
        this.note = this.note+"/"+questionnaire.getIntero().size();

        String strSql = "INSERT INTO NOTE(note, date, idQuest) " +
                        "VALUES('"+this.note+"','"+this.date+"',"+questionnaire.recupId()+")";

        sql.getWritableDatabase().execSQL(strSql);
        Log.d("Note", "La note a été ajoutée");
    }
    //String note, String date,String libelleQuest, Context context
   /*public static ArrayList<Integer>listeIdQuest(Context context){
        ArrayList<Integer> listeResultat = new ArrayList<Integer>();

        Sql sql = new Sql(context);
        String strSql = "SELECT DISTINCT idQuest FROM NOTE";

        Cursor cursor = sql.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();

        if(cursor.moveToFirst()){
            do{
                listeResultat.add(cursor.getInt(0));
            }while(cursor.moveToNext());
        }

       for(int i = 0; i<listeResultat.size(); i++){
           Log.d("test", listeResultat.toString());
       }
        return listeResultat;
   }*/
   public static ArrayList<Resultat> listeResultat(Context context, String libelle){
        ArrayList<Resultat> listRes = new ArrayList<Resultat>();
        Sql sql = new Sql(context);

            String strSql = "SELECT note, date " +
                            "FROM NOTE,QUESTIONNAIRE " +
                            "WHERE NOTE.idQuest=QUESTIONNAIRE.id " +
                            "AND libelle='"+libelle+"'" +
                            "ORDER BY note DESC";

            Cursor cursor = sql.getReadableDatabase().rawQuery(strSql,null);
            cursor.moveToFirst();

            if(cursor.moveToFirst()){
                do{
                    Questionnaire questionnaire = Questionnaire.getQuestionnaire(libelle,context);
                    Resultat resultat = new Resultat(cursor.getString(0), cursor.getString(1),questionnaire,context);
                    listRes.add(resultat);
                }while(cursor.moveToNext());
            }
       for(int i = 0; i<listRes.size(); i++){
           String note= listRes.get(i).getDate();
            Log.d("test",note);
       }
        return listRes;
   }
   /*if(cursor.moveToFirst()){
        do{
            ArrayList<Intero> listIntero = Intero.listeInteroWithId(cursor.getInt(0),context);
            Questionnaire questionnaire = new Questionnaire(Questionnaire.recupLibelleQuest(context,cursor.getInt(0)),listIntero,context);

            String strSql2 = "SELECT note, date FROM NOTE WHERE idQuest = "+cursor.getInt(0)+";";

            Cursor cursor2 = sql.getReadableDatabase().rawQuery(strSql2,null);
            cursor2.moveToFirst();

            if(cursor2.moveToFirst()){
                do{
                    Resultat resultat = new Resultat(cursor2.getString(0),cursor2.getString(1),questionnaire,context);
                    listeResultat.add(resultat);

                }while(cursor.moveToNext());
            }
        }while(cursor.moveToNext());
    }*/
   public static ArrayList<Questionnaire> listeQuestionnaireResult(Context context){
        ArrayList<Questionnaire> listQuest = new ArrayList<Questionnaire>();
        Sql sql = new Sql(context);

        String strSql = "SELECT DISTINCT idQuest FROM NOTE";

        Cursor cursor = sql.getReadableDatabase().rawQuery(strSql,null);
        cursor.moveToFirst();

        if(cursor.moveToFirst()){
            do{
                String libelleQuest = Questionnaire.recupLibelleQuest(context, cursor.getInt(0));
                ArrayList<Intero> listIntero = Intero.listeDintero(libelleQuest,context);
                Questionnaire questionnaire= new Questionnaire(libelleQuest,listIntero,context);
                listQuest.add(questionnaire);
            }while(cursor.moveToNext());
        }
        return listQuest;
   }
}
