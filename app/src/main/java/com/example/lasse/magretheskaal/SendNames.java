package com.example.lasse.magretheskaal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendNames extends AppCompatActivity {

    BetweenScreen bt = new BetweenScreen();
    final LogicLayer logic = new LogicLayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_names);


        Button info3 = (Button) findViewById(R.id.BTN_Info3);
        info3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SendNames.this, Info3.class));
            }
        });

        Button next = (Button) findViewById(R.id.BTN_NextSend);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SendNames.this, BetweenScreen.class));
                bt.setLogic(logic);
            }
        });




        Button send = (Button) findViewById(R.id.BTN_Send);
        final EditText name = (EditText) findViewById(R.id.TB_SendName);
        name.clearFocus();
        final TextView counter = (TextView) findViewById(R.id.TV_counter);
        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                logic.addToNames(name.getText().toString());
                counter.setText("Counter: " + logic.displayNumberOfNames());
                name.setText("");
            }
        });

        name.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                name.setText("");
            }
        });

    }

}
