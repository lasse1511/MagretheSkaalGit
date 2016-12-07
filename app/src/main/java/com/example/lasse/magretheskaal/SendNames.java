package com.example.lasse.magretheskaal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SendNames extends AppCompatActivity {

    LogicLayer logic;
    String gameName;
    boolean isCreator = true;
    DatabaseReference myRef;
    int counter_;

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


        final TextView counter = (TextView) findViewById(R.id.TV_counter);
        gameName = getIntent().getExtras().getString("gameName");
        isCreator = getIntent().getExtras().getBoolean("isCreator");
        logic= new LogicLayer(gameName,isCreator,this);
        Button next = (Button) findViewById(R.id.BTN_NextSend);
        logic.RoundTime = getIntent().getExtras().getInt("RoundTime");
        logic.RoundType = getIntent().getExtras().getStringArrayList("RoundType");


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        //Usynliggøre knappen "next" for joiners
        if (isCreator == false)
            next.setVisibility(View.INVISIBLE);



        //Sætter titlen på siden
        Toolbar toolb = (Toolbar) findViewById(R.id.toolbar);
        toolb.setTitle(gameName);

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

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener(){

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


        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                counter_++;
                counter.setText("Counter: " + Integer.toString(counter_));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        myRef.addChildEventListener(childEventListener);






        Button send = (Button) findViewById(R.id.BTN_Send);
        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View alertView){

                myRef.child(gameName).push().setValue(name.getText().toString());
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

    //Håndtering af tilbageknappen
    @Override
    public void onBackPressed()
    {
        if (isCreator == true) {
            final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            final Intent i = new Intent(this, SetupGame.class);
            builder1.setTitle("Previous screen");
            builder1.setMessage("Are you sure you want to go back? The list of names will be lost.");
            builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    i.putExtra("gameName", gameName);
                    startActivity(i);
                }
            });

            builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Do nothing
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder1.create();
            alert.show();
        }
        else {
            final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            final Intent i = new Intent(this, CreateGame.class);
            builder1.setTitle("Previous screen");
            builder1.setMessage("Are you sure you want to go back? You can always join the game again.");
            builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    startActivity(i);
                }
            });

            builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Do nothing
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder1.create();
            alert.show();
        }

    }

}
