package net.peyrache.appvocab.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.peyrache.appvocab.R;
import net.peyrache.appvocab.view.creationQuestionnaire.Creation_Quest;
import net.peyrache.appvocab.view.jouer.Lancer_Quest;
import net.peyrache.appvocab.view.resultat.ResultatMenu;

public class MainActivity extends AppCompatActivity {

    private Button btCreation, btSelection, btResult;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        this.btCreation = findViewById(R.id.btCreation);
        this.btSelection = findViewById(R.id.btLance);
        this.btResult = findViewById(R.id.btResult);

        ecouteurBtCrea();
        ecouteurBtSelec();
        ecouteurBtResult();
    }

    private void ecouteurBtCrea() {
        btCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, Creation_Quest.class);
                startActivity(intent);
            }
        });
    }

    private void ecouteurBtSelec() {
        btSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, Lancer_Quest.class);
                startActivity(intent);
            }
        });
    }

    private void ecouteurBtResult() {
        btResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ResultatMenu.class);
                startActivity(intent);
            }
        });
    }
}