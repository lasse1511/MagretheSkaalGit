package com.example.lasse.magretheskaal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        LogicLayer logic = new LogicLayer();

        logic.Team1Score = getIntent().getExtras().getInt("Team1Score");
        logic.Team2Score = getIntent().getExtras().getInt("Team2Score");
        logic.NamesOrg = getIntent().getExtras().getStringArrayList("NamesOrg");

        TextView winner = (TextView) findViewById(R.id.Text_winner);
        TextView t1 = (TextView) findViewById(R.id.Text_ENDTeam1Score);
        TextView t2 = (TextView) findViewById(R.id.Text_ENDTeam2Score);

        if (logic.Team1Score > logic.Team2Score)
            winner.setText("Team 1 won - CONGRATULATIONS!");
        else if (logic.Team2Score > logic.Team1Score)
            winner.setText("Team 2 won - CONGRATULATIONS!");
        else if (logic.Team1Score == logic.Team2Score)
            winner.setText("It's a tie - play another round!");

        t1.setText("Team 1 score: " + logic.Team1Score);
        t2.setText("Team 2 score: " + logic.Team2Score);


    }

}
