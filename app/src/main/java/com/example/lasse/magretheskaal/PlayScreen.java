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

        builder.setTitle("Round Finished");
        builder.setMessage("Next round");
        final AlertDialog alert = builder.create();
        TextView names = (TextView) findViewById(R.id.text_namesPlay);
        Button right = (Button) findViewById(R.id.BTN_right);

        logic.NamesOrg = getIntent().getExtras().getStringArrayList("NamesOrg");
         logic.NamesEdit = getIntent().getExtras().getStringArrayList("NamesEdit");
        logic.RoundTime =getIntent().getExtras().getInt("RoundTime");
        logic.RoundType = getIntent().getExtras().getStringArrayList("RoundType");
        logic.Team1Score = getIntent().getExtras().getInt("Team1Score");
        logic.Team2Score = getIntent().getExtras().getInt("Team2Score");



        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        final Intent i = new Intent(PlayScreen.this, BetweenScreen.class);

        final TextView mTextField = (TextView) findViewById(R.id.Countdowntimer);
        Integer rt = (logic.getRoundTime()*1000)+1000;
        new CountDownTimer(rt, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText("seconds remaining: " + Long.toString((millisUntilFinished / 1000)-1));
                //here you can have your logic to set text to edittext
               if((millisUntilFinished/1000)==1)
                {

                    alert.show();


                    i.putExtra("NamesOrg", logic.NamesOrg);
                    i.putExtra("NamesEdit", logic.NamesEdit);
                    i.putStringArrayListExtra("RoundType", logic.RoundType);
                    i.putExtra("RoundTime", logic.RoundTime);
                    i.putExtra("Team1Score", logic.Team1Score);
                    i.putExtra("Team2Score", logic.Team2Score);
                }
            }



            public void onFinish() {


                startActivity(i);
                alert.dismiss();
            }


        }.start();

        /*index = rand.nextInt(logic.NamesEdit.size());
        names.setText(logic.NamesEdit.get(index));
*/

        }





    public void setLogic(LogicLayer logic_)
    {
        logic = logic_;
    }

}
