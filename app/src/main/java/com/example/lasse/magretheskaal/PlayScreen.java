package com.example.lasse.magretheskaal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class PlayScreen extends AppCompatActivity {

    private LogicLayer logic;
    int index = 0;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        TextView names = (TextView) findViewById(R.id.text_namesPlay);
        Button right = (Button) findViewById(R.id.BTN_right);


        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        index = rand.nextInt(logic.NamesEdit.size());
        names.setText(logic.NamesEdit.get(index));


        }





    public void setLogic(LogicLayer logic_)
    {
        logic = logic_;
    }

}
