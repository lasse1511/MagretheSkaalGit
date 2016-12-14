package com.example.lasse.magretheskaal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateGame extends AppCompatActivity {

    EditText input;
    AlertDialog alert;
    AlertDialog alert1;
    Intent i;
    Intent a;
    String gameName;
    Button join;
    boolean isCreator;
    Button start;
    DatabaseReference myRef;
    DatabaseReference myRefChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_create_game);

        i = new Intent(this, SetupGame.class);
        input = new EditText(this);
        a = new Intent(this, SendNames.class);

        i.putExtra("isCreator", isCreator);
        alert = builderMethodCreate(i);

        a.putExtra("isCreator", isCreator);
        alert1 = builderMethodJoin(a);

        start = (Button) findViewById(R.id.BTN_create);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                myRef = database.getReference();
                alert.show();
            }
        });


        join = (Button) findViewById(R.id.BTN_join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert1.show();
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

    public AlertDialog builderMethodCreate(final Intent i)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(CreateGame.this)
                .setTitle("Name of the game")
                .setMessage("Type in the name of the game")
                .setView(input)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int w) {
                        gameName = input.getText().toString();
                        i.putExtra("gameName", gameName);

                        myRef.child(gameName).push().setValue("False3401");

                        startActivity(i);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int w) {
                }
                });
        return builder.create();
    }

    public AlertDialog builderMethodJoin(final Intent i)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(CreateGame.this)
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
        return builder.create();
    }


}
