package net.peyrache.appvocab.view.resultat;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import net.peyrache.appvocab.R;
import net.peyrache.appvocab.modele.Questionnaire;
import net.peyrache.appvocab.view.adapter.Resultat_Main_Adapter;

public class ResultatMenu extends AppCompatActivity {

    private ListView lvChoixQuestResult;
    private Button btRetourResultMenu;
    private Resultat_Main_Adapter resultat_main_adapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_menu);
        init();
    }

    private void init() {
        lvChoixQuestResult = findViewById(R.id.lvChoixQuestResult);
        btRetourResultMenu = findViewById(R.id.btRetourResultMenu);

        resultat_main_adapter = new Resultat_Main_Adapter(ResultatMenu.this);
        lvChoixQuestResult.setAdapter(resultat_main_adapter);

        ecouteurBtRetourResultMenu();
        lvEcouteur();
    }

    private void ecouteurBtRetourResultMenu() {
        btRetourResultMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void lvEcouteur(){
        lvChoixQuestResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Ici par exemple je suis obligé de créer un objet Questionnaire
                Questionnaire questionnaire = (Questionnaire) lvChoixQuestResult.getItemAtPosition(position);
                intent = new Intent(ResultatMenu.this,ResultatNote.class);
                intent.putExtra("libelleQuest",questionnaire.getLibelle());
                startActivity(intent);
            }
        });
    }
}