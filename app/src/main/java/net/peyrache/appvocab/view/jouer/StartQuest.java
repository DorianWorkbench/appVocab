package net.peyrache.appvocab.view.jouer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.peyrache.appvocab.R;
import net.peyrache.appvocab.controller.CLancerQuest;
import net.peyrache.appvocab.modele.Questionnaire;

import java.util.Random;

public class StartQuest extends AppCompatActivity {

    private TextView lblNomQuestionnaire, lblNote, lblQr, lblQuestion;
    private EditText etRep;
    private Button btValider, btRetour;
    private Intent intent;
    private CLancerQuest cLancerQuest;
    Questionnaire questionnaire;
    private Random rand;

    private Integer intRand, incNote, nbQuest,nbQr;
    private String nomQuestionaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quest);
        init();
    }

    private void init() {
        rand = new Random();

        lblNomQuestionnaire = findViewById(R.id.startLblNomQuest);
        lblNote = findViewById(R.id.startLblNote);
        lblQr = findViewById(R.id.startLblNbQuestion);
        lblQuestion = findViewById(R.id.startLblQuestion);
        etRep = findViewById(R.id.startEtRep);
        btValider = findViewById(R.id.startBtValider);
        btRetour = findViewById(R.id.startBtRetour);

        intent = getIntent();
        nomQuestionaire = intent.getStringExtra("libelleQuestionnaire");

        cLancerQuest = new CLancerQuest(StartQuest.this);

        //Je suis obligé d'instancier un questionnaire pour avoir accès aux méthodes de classe.
        questionnaire = cLancerQuest.getQuestionnaire(nomQuestionaire);

        intRand = rand.nextInt((questionnaire.getIntero().size()-1)+1);

        nbQuest = questionnaire.getIntero().size();
        nbQr = questionnaire.getIntero().size();

        incNote = 0;
        lblNomQuestionnaire.setText(questionnaire.getLibelle());
        lblNote.setText("Note : "+incNote.toString()+"/"+nbQuest.toString());
        lblQr.setText("QR : "+nbQr.toString());
        lblQuestion.setText(questionnaire.getIntero().get(intRand).getIntituleQuest());

        ecouteurBtRetour();
        ecouteurBtValider();
    }



    private void ecouteurBtRetour() {
        btRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void ecouteurBtValider() {
        btValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(etRep.getText().toString().isEmpty())){
                    if(testReponseUtil()){
                        incNote++;
                        lblNote.setText("Note : "+incNote.toString()+"/"+nbQuest.toString());

                        changementQuestion();

                        if(!questionnaire.getIntero().isEmpty())
                            Toast.makeText(StartQuest.this, "Bravo", Toast.LENGTH_SHORT).show();

                    }else{
                        changementQuestion();

                        if(!questionnaire.getIntero().isEmpty())
                            Toast.makeText(StartQuest.this, "Mauvaise réponse", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(StartQuest.this, "Veuillez saisir une réponse", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean testReponseUtil(){
        //Récupération pour la création de l'objet de test
        String questionSTR = lblQuestion.getText().toString();
        String reponseSTR = etRep.getText().toString();

        //Test
        Boolean testIntero = cLancerQuest.verifIntero(questionnaire,cLancerQuest.creationIntero(questionSTR,reponseSTR));

        return testIntero;
    }
    private void changementQuestion(){
        //Si on peut enlever une question à l'array.
        try{
            etRep.setText("");
            //Recuperation de l'array Intero du questionnaire courant puis suppression de l'objet Intero courrant et réattribution d'un intRand
            Log.d("idRand", intRand.toString());
            questionnaire.getIntero().remove(questionnaire.getIntero().get(intRand));
            intRand = rand.nextInt((questionnaire.getIntero().size()-1)+1);

            //Affichage nouvelle question
            lblQuestion.setText(questionnaire.getIntero().get(intRand).getIntituleQuest());

            //On décrémentation et affichage
            nbQr--;
            lblQr.setText("QR : "+nbQr.toString());

            Integer tailleQuestionnaire = questionnaire.getIntero().size();
            Log.d("tailleQ", tailleQuestionnaire.toString());
        }//Si l'array est vide et que l'on essaie d'enlever un element de l'array
        catch (Exception e){
            Toast.makeText(this, "Score de "+lblNote.getText().toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Note_Affichage.class);
            intent.putExtra("Score", incNote.toString());
            intent.putExtra("Questionnaire", nomQuestionaire);
            startActivity(intent);
            finish();
        }
    }
}