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
    int rt;
    TextView names;
    AlertDialog.Builder builder;
    AlertDialog.Builder builder1;
    AlertDialog alert;
    AlertDialog alert1;
    AlertDialog PauseA;
    AlertDialog.Builder PauseAlert;

    Intent i;
    Intent iEnd;

    CountDownTimer CDT;

    Button right;
    Button pass;
    Button pause;
    TextView mTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        builder = new AlertDialog.Builder(PlayScreen.this);
        builder1 = new AlertDialog.Builder(PlayScreen.this);

        //Den rundetid som gemmes når man trykker pause


        //Initiering af Intents
        i = new Intent(this, BetweenScreen.class);
        iEnd = new Intent(this,EndScreen.class);

        //De to meddelelser som popper op i PS
        builder.setTitle("Round Finished");
        builder.setMessage("Next round");
        alert = builder.create();

        builder1.setTitle("Round Finished");
        builder1.setMessage("No more names in list");
        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                startActivity(i);
            }
        });
        alert1 = builder1.create();

        //set pause alert
        PauseAlert = new AlertDialog.Builder(this);


        //Initiering af diverse knapper osv.
        names = (TextView) findViewById(R.id.text_namesPlay);
        right = (Button) findViewById(R.id.BTN_right);
        pass = (Button) findViewById(R.id.BTN_pass);
        pause = (Button) findViewById(R.id.BTN_PlayPAUSE);
        mTextField = (TextView) findViewById(R.id.Countdowntimer);

        //Custom metode som sætter det lokale logikobjekts attributter til hvad end intentet smider med
        getIntentExtras();


        //Rundetiden hentes fra logiklaget
        rt = (logic.getRoundTime()*1000)+1000;

        CDT = cTimer();
        CDT.start();

        //Bestemmer hvad der sker når man trykker "End game"
        final Intent isEnd = new Intent(this, CreateGame.class);
        final AlertDialog.Builder SureAlert = new  AlertDialog.Builder(this);
        SureAlert.setTitle("Sure?");
        SureAlert.setMessage("Are you sure?");
        SureAlert.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            startActivity(isEnd);

            }
        });
        SureAlert.setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                PauseA.show();
            }
        });
        final AlertDialog SureEnd = SureAlert.create();



        //Bestemmer hvad der sker når man trykker "Skip Round"
        final AlertDialog.Builder SureSkipBuild = new  AlertDialog.Builder(this);
        SureSkipBuild.setTitle("Sure?");
        SureSkipBuild.setMessage("Are you sure?");
        SureSkipBuild.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                logic.RoundType.remove(0);

                if (logic.RoundType.size() == 0)
                {
                    updateIntentEnd();
                    startActivity(iEnd);

                }
                else {
                    right.setActivated(false);
                    pass.setActivated(false);
                    logic.NamesEdit = logic.NamesOrg;
                    logic.RoundCounter++;
                    updateIntentAll();
                    startActivity(i);
                }

            }
        });
        SureSkipBuild.setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                PauseA.show();
            }
        });
        final AlertDialog SureSkip = SureSkipBuild.create();


        //Bygger pauseknappen/dialogen
        PauseAlert.setNegativeButton("Skip Round", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                SureSkip.show();
            }
        });
        PauseAlert.setPositiveButton("Resume", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                rt += 1000;
                CDT = cTimer();
                CDT.start();
            }
        });
        PauseAlert.setNeutralButton("End Game", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                SureEnd.show();
            }
        });
        PauseAlert.setTitle("Pause");
        PauseAlert.setMessage("Pause");
        PauseA = PauseAlert.create();



        //Dette sker når vi trykker på pause
        pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                PauseA.show();
                CDT.cancel();

            }
        });

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
                        updateIntentEnd();
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
                        updateIntentAll();
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


    public void updateIntentEnd()
    {
        iEnd.putExtra("Team1Score", logic.Team1Score);
        iEnd.putExtra("Team2Score", logic.Team2Score);
        iEnd.putExtra("NamesOrg", logic.NamesOrg);
    }

    public void updateIntentAll()
    {
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

    public void getIntentExtras()
    {
        logic.NamesOrg = getIntent().getExtras().getStringArrayList("NamesOrg");
        logic.NamesEdit = getIntent().getExtras().getStringArrayList("NamesEdit");
        logic.RoundTime =getIntent().getExtras().getInt("RoundTime");
        logic.RoundType = getIntent().getExtras().getStringArrayList("RoundType");
        logic.Team1Score = getIntent().getExtras().getInt("Team1Score");
        logic.Team2Score = getIntent().getExtras().getInt("Team2Score");
        logic.RoundCounter = getIntent().getExtras().getInt("RoundCounter");
        logic.Team1Score = getIntent().getExtras().getInt("Team1Score");
        logic.Team2Score = getIntent().getExtras().getInt("Team2Score");

    }


    public CountDownTimer cTimer()
    {

        //En nedtælling starter og ved 1 sekund tilbage begynder processen om at samle intent
        final CountDownTimer CDT = new CountDownTimer(rt, 1000){
            public void onTick(long millisUntilFinished) {
                mTextField.setText("Seconds remaining: " + Long.toString((millisUntilFinished / 1000)-1));
                rt = rt - 1000;
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


        };

        return CDT;

    }


    //Disabling af tilbageknappen i PS
    @Override
    public void onBackPressed()
    {

    }

}
