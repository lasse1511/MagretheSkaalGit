package com.example.lasse.magretheskaal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendNames extends AppCompatActivity {

    LogicLayer logic = new LogicLayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_names);

        final EditText name = (EditText) findViewById(R.id.TB_SendName);
        name.setSelected(false);


        Button info3 = (Button) findViewById(R.id.BTN_Info3);
        info3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SendNames.this, Info3.class));
            }
        });

        logic.RoundTime = getIntent().getExtras().getInt("RoundTime");
        logic.RoundType = getIntent().getExtras().getStringArrayList("RoundType");

        Button next = (Button) findViewById(R.id.BTN_NextSend);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SendNames.this);

                builder.setTitle("Confirm");
                builder.setMessage("Let the games begin?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        logic.NamesEdit = logic.NamesOrg;
                        Intent i = new Intent(SendNames.this, BetweenScreen.class);
                        i.putExtra("NamesEdit", logic.NamesEdit);
                        i.putExtra("NamesOrg", logic.NamesOrg);
                        i.putStringArrayListExtra("RoundType", logic.RoundType);
                        i.putExtra("RoundTime", logic.getRoundTime());
                        startActivity(i);
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();



            }
        });




        final TextView counter = (TextView) findViewById(R.id.TV_counter);

        Button send = (Button) findViewById(R.id.BTN_Send);
        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View alertView){

                logic.addToNames(name.getText().toString());
                counter.setText("Counter: " + Integer.toString(logic.displayNumberOfNames()));
                name.setText("");
            }
        });

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                name.setText("");
            }
        });

    }

}
