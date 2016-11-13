package com.example.lasse.magretheskaal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BetweenScreen extends AppCompatActivity {

    private LogicLayer logic = new LogicLayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_between_screen);

        //Hentning af data fra den tidligere screen
        logic.NamesOrg = getIntent().getExtras().getStringArrayList("NamesOrg");
        logic.NamesEdit = getIntent().getExtras().getStringArrayList("NamesEdit");
        logic.RoundTime = getIntent().getExtras().getInt("RoundTime");
        logic.RoundType = getIntent().getExtras().getStringArrayList("RoundType");
        logic.RoundCounter = getIntent().getExtras().getInt("RoundCounter");
        logic.Team1Score = getIntent().getExtras().getInt("Team1Score");
        logic.Team2Score = getIntent().getExtras().getInt("Team2Score");

        //opdatering af Tekst
        TextView Team1Score = (TextView) findViewById(R.id.Text_ENDTeam1Score);
        TextView Team2Score = (TextView) findViewById(R.id.Text_Team2score);
        TextView WhosRound = (TextView) findViewById(R.id.Text_WhosRound);
        TextView WhichRound = (TextView) findViewById(R.id.Text_RoundBetween);

        //Score
        int T1S = logic.Team1Score;
        int T2S = logic.Team2Score;

        //Score i Tekstvindue
        Team1Score.setText("Team 1 score: " + Integer.toString(T1S));
        Team2Score.setText("Team 2 score: " + Integer.toString(T2S));

        //Betstemmelse af hvis tur det er næste gang
        if (logic.RoundCounter % 2 == 0)
            WhosRound.setText("Team 1 - get ready!");
        else if (logic.RoundCounter % 2 == 1)
            WhosRound.setText("Team 2 - get ready!");

        //Rundetype
        WhichRound.setText(logic.RoundType.get(0).toString());

         //start playScreen
        Button next = (Button) findViewById(R.id.BTN_StartBetween);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(BetweenScreen.this, PlayScreen.class);
                i.putStringArrayListExtra("NamesOrg", logic.NamesOrg);
                i.putExtra("NamesEdit", logic.NamesEdit);
                i.putStringArrayListExtra("RoundType", logic.RoundType);
                i.putExtra("RoundTime", logic.getRoundTime());
                i.putExtra("Team1Score", logic.Team1Score);
                i.putExtra("Team2Score", logic.Team2Score);
                i.putExtra("RoundCounter", logic.RoundCounter);
                i.putExtra("Team1Score", logic.Team1Score);
                i.putExtra("Team2Score", logic.Team2Score);
                startActivity(i);
            }
        });
    }
}
