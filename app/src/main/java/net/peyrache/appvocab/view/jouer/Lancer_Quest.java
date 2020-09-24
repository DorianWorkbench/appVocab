package net.peyrache.appvocab.view.jouer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import net.peyrache.appvocab.R;
import net.peyrache.appvocab.modele.Questionnaire;
import net.peyrache.appvocab.view.adapter.Lancer_Quest_Adapter;

public class Lancer_Quest extends AppCompatActivity {

    private ListView lvQuestionnaire;
    private Button btRetourLancer;
    private Intent intent;
    private Lancer_Quest_Adapter lancer_quest_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancer__quest);
        init();
    }

    private void init() {
        lvQuestionnaire = findViewById(R.id.lvQuestionnaire);
        btRetourLancer = findViewById(R.id.btRetourLancer);

        lancer_quest_adapter = new Lancer_Quest_Adapter(Lancer_Quest.this);
        lvQuestionnaire.setAdapter(lancer_quest_adapter);
        ecouteurBtLancerRetour();
        ecouteurLvQuestionnaire();
    }

    private void ecouteurBtLancerRetour() {
        btRetourLancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ecouteurLvQuestionnaire() {
        lvQuestionnaire.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //A revoir pour faire ça propre (ne pas importer le questionnaire)

                Questionnaire questionnaire = (Questionnaire)lvQuestionnaire.getItemAtPosition(position);

                intent = new Intent(Lancer_Quest.this,StartQuest.class);
                intent.putExtra("libelleQuestionnaire", questionnaire.getLibelle());
                startActivity(intent);
                finish();
            }
        });
    }
}