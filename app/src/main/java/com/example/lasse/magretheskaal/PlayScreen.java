package com.example.lasse.magretheskaal;

import android.app.AlertDialog;
import android.content.DialogInterface;
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


        //Initiering af Intents
        final Intent i = new Intent(this, BetweenScreen.class);
        final Intent iEnd = new Intent(this,EndScreen.class);

        //De to meddelelser som popper op i PS
        builder.setTitle("Round Finished");
        builder.setMessage("Next round");
        final AlertDialog alert = builder.create();

        builder1.setTitle("Round Finished");
        builder1.setMessage("No more names in list");
        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                startActivity(i);
            }
        });
        final AlertDialog alert1 = builder1.create();



        //Initiering af diverse knapper osv.
        final TextView names = (TextView) findViewById(R.id.text_namesPlay);
        final Button right = (Button) findViewById(R.id.BTN_right);
        final Button pass = (Button) findViewById(R.id.BTN_pass);
        final Button pause = (Button) findViewById(R.id.BTN_PlayPAUSE);
        final TextView mTextField = (TextView) findViewById(R.id.Countdowntimer);




        //Gemning af attributterne fra BW
        logic.NamesOrg = getIntent().getExtras().getStringArrayList("NamesOrg");
        logic.NamesEdit = getIntent().getExtras().getStringArrayList("NamesEdit");
        logic.RoundTime =getIntent().getExtras().getInt("RoundTime");
        logic.RoundType = getIntent().getExtras().getStringArrayList("RoundType");
        logic.Team1Score = getIntent().getExtras().getInt("Team1Score");
        logic.Team2Score = getIntent().getExtras().getInt("Team2Score");
        logic.RoundCounter = getIntent().getExtras().getInt("RoundCounter");
        logic.Team1Score = getIntent().getExtras().getInt("Team1Score");
        logic.Team2Score = getIntent().getExtras().getInt("Team2Score");

        //Dette sker når vi trykker på pause
        pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

            }
        });



        //Rundetiden hentes fra logiklaget
        Integer rt = (logic.getRoundTime()*1000)+1000;

        //En nedtælling starter og ved 1 sekund tilbage begynder processen om at samle intent
        final CountDownTimer CDT = new CountDownTimer(rt, 1000){
            public void onTick(long millisUntilFinished) {
                mTextField.setText("Seconds remaining: " + Long.toString((millisUntilFinished / 1000)-1));
                //here you can have your logic to set text to edittext
                if((millisUntilFinished/1000)==1)
                {
                    alert.show();
                    logic.RoundCounter++;
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


        //Når tidtælleren er færdig startes aktivitet som blev redigeret ovenover
            public void onFinish() {


                startActivity(i);
                alert.dismiss();
            }


        }.start();


        //Visning af det første navn
        if (logic.NamesEdit.size() > 1)
        {
            index = rand.nextInt(logic.NamesEdit.size()-1);
            names.setText(logic.NamesEdit.get(index));
            logic.removeName(index);
        }
        else if (logic.NamesEdit.size() == 1)
        {
            index = 0;
            names.setText(logic.NamesEdit.get(index));
            logic.removeName(index);
        }


        //Hvis listen ikke er tom når knappen bliver trykket får det aktive hold et point, hvis den er tom sluttes runden og BW vises
        right.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                logic.teamScores();
                if (logic.NamesEdit.size() > 1)
                {
                    index = rand.nextInt(logic.NamesEdit.size()-1);
                    names.setText(logic.NamesEdit.get(index));
                    logic.removeName(index);
                }
                else if (logic.NamesEdit.size() == 1)
                {
                    index = 0;
                    names.setText(logic.NamesEdit.get(index));
                    logic.removeName(index);
                }
                else if (logic.NamesEdit.size() == 0)
                {
                    if (logic.RoundType.size() == 1)
                    {
                        iEnd.putExtra("Team1Score", logic.Team1Score);
                        iEnd.putExtra("Team2Score", logic.Team2Score);
                        iEnd.putExtra("NamesOrg", logic.NamesOrg);
                        CDT.cancel();
                        startActivity(iEnd);
                    }
                    else
                    {
                        alert1.show();
                        CDT.cancel();
                        right.setActivated(false);
                        pass.setActivated(false);
                        logic.RoundType.remove(0);
                        logic.NamesEdit = logic.NamesOrg;
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
                    }
                }
            }
        });

        //Et nyt navn vises hvis der trykkes pas:
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (logic.NamesEdit.size() > 1) {
                    index = rand.nextInt(logic.NamesEdit.size() - 1);
                    names.setText(logic.NamesEdit.get(index));
                }

            }
        });





        }

}
