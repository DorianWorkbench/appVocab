package net.peyrache.appvocab.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Sql extends SQLiteOpenHelper {

    public Sql(Context context) {
        super(context, "appVocab.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "CREATE TABLE QUESTIONNAIRE("
                        +" id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        +" libelle TEXT NOT NULL)";
        db.execSQL(strSql);

        strSql = "CREATE TABLE NOTE("
                 +"id INTEGER PRIMARY KEY AUTOINCREMENT,"
                 +"note TEXT NOT NULL,"
                 +"date TEXT NOT NULL,"
                 +"idQuest INTEGER NOT NULL,"
                 +"FOREIGN KEY(idQuest) REFERENCES QUESTIONNAIRE(id))";
        db.execSQL(strSql);

        strSql = "CREATE TABLE QUESTIONREP("
                 +" id INTEGER PRIMARY KEY AUTOINCREMENT,"
                 +"intituleQuest TEXT NOT NULL,"
                 +"intituleRep TEXT NOT NULL,"
                 +"idQuest INTEGER NOT NULL,"
                 +"FOREIGN KEY (idQuest) REFERENCES QUESTIONNAIRE(idQuest))";
        db.execSQL(strSql);

        Log.d("Bdd", "onCreate invoke");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
