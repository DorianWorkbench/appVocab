package net.peyrache.appvocab.view.creationQuestionnaire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.peyrache.appvocab.R;
import net.peyrache.appvocab.controller.CCreaQuest;
import net.peyrache.appvocab.modele.Intero;
import net.peyrache.appvocab.view.MainActivity;

import java.util.ArrayList;

public class AjouterQuestRep extends AppCompatActivity {

    private EditText etQuest, etRep;
    private Button btSuivant, btAnnuler, btTerminer;
    private TextView tvNomQuest, tvNbQuestion;

    private String nomQuesti;
    private Integer nbQuestion;
    private ArrayList<Intero> intero;
    private CCreaQuest ccreaQuest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_quest_rep);
        init();
    }

    private void init() {
        etQuest = findViewById(R.id.etQuestion);
        etRep = findViewById(R.id.etReponse);
        btAnnuler = findViewById(R.id.btAnnuler);
        btSuivant = findViewById(R.id.btValider);
        btTerminer = findViewById(R.id.btTerminer);
        tvNomQuest = findViewById(R.id.tvNomQuestio);
        tvNbQuestion = findViewById(R.id.tvNbEn);
        nbQuestion = 0;
        ccreaQuest = new CCreaQuest(AjouterQuestRep.this);
        intero = new ArrayList<Intero>();
        this.nomQuesti = getIntent().getStringExtra("nomQuest");
        tvNomQuest.setText("Quest. : "+nomQuesti);

        ecouteurBtSuivant();
        ecouteurBtAnnuler();
        ecouteurBtTerminer();
    }
    private void ecouteurBtSuivant() {
        btSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etQuestSTR = etQuest.getText().toString();
                String etRepSTR = etRep.getText().toString();
                if(!(etQuestSTR.isEmpty() || etRepSTR.isEmpty())){

                    Intero uneIntero = new Intero(etQuestSTR,etRepSTR);

                    etQuest.setText("");
                    etRep.setText("");

                    if(!(questExist(uneIntero.getIntituleQuest()))){
                        intero.add(uneIntero);

                        Integer nbInteros = intero.size();

                        Toast.makeText(AjouterQuestRep.this, nbInteros.toString(), Toast.LENGTH_SHORT).show();
                        compteurQuest();
                    }else{
                        Toast.makeText(AjouterQuestRep.this, "La question existe déjà", Toast.LENGTH_SHORT).show();
                    }  
                }else{
                    Toast.makeText(AjouterQuestRep.this, "Veuillez saisir les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void ecouteurBtAnnuler() {
        btAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void ecouteurBtTerminer() {
        btTerminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ccreaQuest.creationQuestionnaire(nomQuesti, intero);
                Toast.makeText(AjouterQuestRep.this, "Votre questionnaire a bien été créé", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
    private void compteurQuest(){
        nbQuestion+=1;
        this.tvNbQuestion.setText("nbQuest : "+nbQuestion.toString());
    }
    private Boolean questExist(String questLibelle){
        Boolean result = false;
        for(Intero uneIntero:this.intero){
            if(uneIntero.getIntituleQuest().equals(questLibelle)){
                result = true;
            }else{
                result = false;
            }
        }
        return result;
    }
}