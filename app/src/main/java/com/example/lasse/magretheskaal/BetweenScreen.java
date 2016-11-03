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



        logic.NamesOrg = getIntent().getExtras().getStringArrayList("list");



        Button next = (Button) findViewById(R.id.BTN_StartBetween);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(BetweenScreen.this, PlayScreen.class));
            }
        });

        TextView Team1Score = (TextView) findViewById(R.id.Text_Team1Score);
        TextView Team2Score = (TextView) findViewById(R.id.Text_Team2score);

        int T1S = logic.Team1Score;
        int T2S = logic.Team2Score;


        Team1Score.setText("Team 1 score: " + Integer.toString(T1S));
        Team2Score.setText("Team 2 score: " + Integer.toString(T2S));




    }


    public void setLogic(LogicLayer logic_)
    {
        logic = logic_;
    }

}
