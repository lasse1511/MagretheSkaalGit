package com.example.lasse.magretheskaal;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class PlayScreen extends AppCompatActivity {

    private LogicLayer logic = new LogicLayer();
    int index = 0;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);
        final  AlertDialog.Builder builder = new AlertDialog.Builder(PlayScreen.this);
        final  AlertDialog.Builder builder1 = new AlertDialog.Builder(PlayScreen.this);


        builder.setTitle("Round Finished");
        builder.setMessage("Next round");

        builder1.setTitle("Round Finished");
        builder1.setMessage("No more names in list");

        final AlertDialog alert = builder.create();
        final AlertDialog alert1 = builder1.create();

        final TextView names = (TextView) findViewById(R.id.text_namesPlay);
        final Button right = (Button) findViewById(R.id.BTN_right);
        final Button pass = (Button) findViewById(R.id.BTN_pass);
        final Intent i = new Intent(PlayScreen.this, BetweenScreen.class);


        logic.NamesOrg = getIntent().getExtras().getStringArrayList("NamesOrg");
        logic.NamesEdit = getIntent().getExtras().getStringArrayList("NamesEdit");
        logic.RoundTime =getIntent().getExtras().getInt("RoundTime");
        logic.RoundType = getIntent().getExtras().getStringArrayList("RoundType");
        logic.Team1Score = getIntent().getExtras().getInt("Team1Score");
        logic.Team2Score = getIntent().getExtras().getInt("Team2Score");
        logic.RoundCounter = getIntent().getExtras().getInt("RoundCounter");
        logic.Team1Score = getIntent().getExtras().getInt("Team1Score");
        logic.Team2Score = getIntent().getExtras().getInt("Team2Score");



        //Visning af det første navn
        index =  rand.nextInt(logic.NamesEdit.size());
        names.setText(logic.NamesEdit.get(index));
        logic.removeName(index);



        right.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                logic.teamScores();
                if (logic.NamesEdit.size() > 0)
                {
                    index = rand.nextInt(logic.NamesEdit.size());
                    names.setText(logic.NamesEdit.get(index));
                    logic.removeName(index);
                }
                else
                {
                    alert1.show();
                    right.setActivated(false);
                    pass.setActivated(false);
                    logic.RoundCounter++;
                    i.putExtra("NamesOrg", logic.NamesOrg);
                    i.putExtra("NamesEdit", logic.NamesEdit);
                    i.putStringArrayListExtra("RoundType", logic.RoundType);
                    i.putExtra("RoundTime", logic.RoundTime);
                    i.putExtra("Team1Score", logic.Team1Score);
                    i.putExtra("Team2Score", logic.Team2Score);
                    i.putExtra("RoundCounter", logic.RoundCounter);
                    i.putExtra("Team1Score", logic.Team1Score);
                    i.putExtra("Team2Score", logic.Team2Score);
                    startActivity(i);
                }

            }
        });


        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = rand.nextInt(logic.NamesEdit.size());
                names.setText(logic.NamesEdit.get(index));
            }
        });



        final TextView mTextField = (TextView) findViewById(R.id.Countdowntimer);
        Integer rt = (logic.getRoundTime()*1000)+1000;
        new CountDownTimer(rt, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText("seconds remaining: " + Long.toString((millisUntilFinished / 1000)-1));
                //here you can have your logic to set text to edittext
               if((millisUntilFinished/1000)==1)
                {
                    alert.show();
                    right.setActivated(false);
                    pass.setActivated(false);
                    i.putExtra("NamesOrg", logic.NamesOrg);
                    i.putExtra("NamesEdit", logic.NamesEdit);
                    i.putStringArrayListExtra("RoundType", logic.RoundType);
                    i.putExtra("RoundTime", logic.RoundTime);
                    i.putExtra("Team1Score", logic.Team1Score);
                    i.putExtra("Team2Score", logic.Team2Score);
                    i.putExtra("RoundCounter", logic.RoundCounter);
                    i.putExtra("Team1Score", logic.Team1Score);
                    i.putExtra("Team2Score", logic.Team2Score);
                }
            }



            public void onFinish() {


                startActivity(i);
                alert.dismiss();
            }


        }.start();

        }

}
