package net.peyrache.appvocab.view.creationQuestionnaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.peyrache.appvocab.R;
import net.peyrache.appvocab.controller.CCreaQuest;

public class Creation_Quest extends AppCompatActivity {

    private Button btValider;
    private EditText nomQuest;
    private Intent questRep;
    private CCreaQuest ccreaQuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation__quest);
        init();
    }

    private void init() {
        btValider = findViewById(R.id.btValider);
        nomQuest = findViewById(R.id.nomQuest);
        ccreaQuest= new CCreaQuest(Creation_Quest.this);
        ecouteurBtValider();
    }

    private void ecouteurBtValider() {
        btValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomQuestSTR = nomQuest.getText().toString();

                if(nomQuestSTR.isEmpty()){
                    Toast.makeText(Creation_Quest.this, "veuillez saisir le champ", Toast.LENGTH_SHORT).show();
                }else if(ccreaQuest.questiExist(nomQuestSTR,Creation_Quest.this)){
                    Log.d("testcrea","coucou");
                        Toast.makeText(Creation_Quest.this, "Le questionnaire existe déjà", Toast.LENGTH_SHORT).show();
                }else{
                    questRep = new Intent(Creation_Quest.this,AjouterQuestRep.class);
                    questRep.putExtra("nomQuest", nomQuestSTR);

                    startActivity(questRep);
                    finish();
                }
            }
        });
    }

}