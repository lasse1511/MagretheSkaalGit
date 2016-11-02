package com.example.lasse.magretheskaal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BetweenScreen extends AppCompatActivity {

    private LogicLayer logic;
    private PlayScreen play = new PlayScreen();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_between_screen);

        Button next = (Button) findViewById(R.id.BTN_StartBetween);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(BetweenScreen.this, PlayScreen.class));
                play.setLogic(logic);
            }
        });


    }

    public void setLogic(LogicLayer logic_)
    {
        logic = logic_;
    }

}
