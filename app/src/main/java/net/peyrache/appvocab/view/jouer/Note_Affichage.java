package net.peyrache.appvocab.view.jouer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.peyrache.appvocab.R;
import net.peyrache.appvocab.controller.CResultat;
import net.peyrache.appvocab.modele.Questionnaire;
import net.peyrache.appvocab.modele.Resultat;
import net.peyrache.appvocab.view.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note_Affichage extends AppCompatActivity {

    private TextView lblAffNote;
    private Button btQuitter;
    private Intent intent;
    private CResultat cResultat;
    private Date  date;
    private SimpleDateFormat dateForm;
    private String note, dateActu, nomQuestionnaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__affichage);
        init();
    }

    private void init() {
        lblAffNote = findViewById(R.id.lblAffNote);
        btQuitter = findViewById(R.id.btQuitter);
        note = getIntent().getStringExtra("Score");
        lblAffNote.setText(note);
        cResultat = new CResultat(Note_Affichage.this);
        date = new Date();
        dateForm = new SimpleDateFormat("dd/MM/YYYY");
        dateActu = dateForm.format(date);

        Log.d("dateActu",dateActu);

        nomQuestionnaire = getIntent().getStringExtra("Questionnaire");
        ecouterQuitter();
    }

    private void ecouterQuitter(){
        btQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Resultat resultat = new Resultat(note, dateActu, Questionnaire.getQuestionnaire(nomQuestionnaire, Note_Affichage.this),Note_Affichage.this);
                cResultat.ajoutNote(resultat);
                finish();
            }
        });
    }
}