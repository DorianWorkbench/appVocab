package net.peyrache.appvocab.view.resultat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import net.peyrache.appvocab.R;
import net.peyrache.appvocab.view.adapter.Resultat_Second_Adapter;

public class ResultatNote extends AppCompatActivity {

    private ListView lvNoteQuestResult;
    private Resultat_Second_Adapter resultat_second_adapter;
    private Button retour;
    private Intent intent;
    private String libelleQuest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_note);
        init();
    }

    private void init() {
        lvNoteQuestResult = findViewById(R.id.lvNoteQuestResult);
        retour = findViewById(R.id.btRetourResultNote);
        intent = getIntent();
        libelleQuest = intent.getStringExtra("libelleQuest");
        Log.d("noteLibelle",libelleQuest);
        resultat_second_adapter = new Resultat_Second_Adapter(ResultatNote.this,libelleQuest);
        lvNoteQuestResult.setAdapter(resultat_second_adapter);

        ecouteurRetour();
    }

    private void ecouteurRetour() {
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}