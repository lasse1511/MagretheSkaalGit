package com.example.lasse.magretheskaal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateGame extends AppCompatActivity {

    EditText input;
    AlertDialog alert;
    AlertDialog.Builder builder;
    Intent i;
    Intent a;
    String gameName;
    Button join;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        i = new Intent(this, SetupGame.class);
        input = new EditText(this);
        a = new Intent(this, SendNames.class);

        Button start = (Button) findViewById(R.id.BTN_create);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = builderMethod(i);
                alert = builder.create();
                alert.show();
            }
        });


        join = (Button) findViewById(R.id.BTN_join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = builderMethod(a);
                alert = builder.create();
                alert.show();
            }
        });


        Button info1 = (Button) findViewById(R.id.BTN_Info1);
        info1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(CreateGame.this, Info1.class));
            }
        });
    }

    public AlertDialog.Builder builderMethod(final Intent i)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateGame.this)
                .setTitle("Name of the game")
                .setMessage("Type in the name of the game")
                .setView(input)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int w) {
                        gameName = input.getText().toString();
                        i.putExtra("gameName", gameName);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int w) {

                    }
                });
        return builder;
    }
}
