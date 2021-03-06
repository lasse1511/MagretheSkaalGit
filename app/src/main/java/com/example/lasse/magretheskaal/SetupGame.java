package com.example.lasse.magretheskaal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;

public class SetupGame extends AppCompatActivity {

    private LogicLayer logic = new LogicLayer();

    TextView info1;
    TextView info2;
    TextView info3;
    TextView info4;
    TextView info5;
    String gameName;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_game);

        info1 = (TextView) findViewById(R.id.Text_SetupInfo1);
        info2 = (TextView) findViewById(R.id.Text_SetupInfo2);
        info3 = (TextView) findViewById(R.id.Text_SetupInfo3);
        info4 = (TextView) findViewById(R.id.Text_SetupInfo4);
        info5 = (TextView) findViewById(R.id.Text_SetupInfo5);

        i = new Intent(SetupGame.this, SendNames.class);

        //set diverse objekter på interfacet
        final NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);
        final EditText SRound = (EditText) findViewById(R.id.editText_SRound);
        final CheckBox Special = (CheckBox) findViewById(R.id.checkBox7);
        final CheckBox oWord = (CheckBox) findViewById(R.id.checkBox6);
        final CheckBox Mine = (CheckBox) findViewById(R.id.checkBox5);
        final CheckBox FreeS = (CheckBox) findViewById(R.id.checkBox2);
        final EditText SpecialT = (EditText) findViewById(R.id.editText_SRound);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        //modtager gameName fra startskærm
        gameName = getIntent().getExtras().getString("gameName");

        //Sætter titlen på siden
        Toolbar toolb = (Toolbar) findViewById(R.id.toolbar);
        toolb.setTitle(gameName);


        //Sæt standartval af rundetyper
        oWord.setChecked(true);
        Mine.setChecked(true);
        FreeS.setChecked(true);

        // vis infp
        Button info2_BTN = (Button) findViewById(R.id.BTN_Info2);
        info2_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(SetupGame.this, Info2.class));
                if (info1.getVisibility() == View.VISIBLE)
                {
                    info1.setVisibility(View.INVISIBLE);
                    info2.setVisibility(View.INVISIBLE);
                    info3.setVisibility(View.INVISIBLE);
                    info4.setVisibility(View.INVISIBLE);
                    info5.setVisibility(View.INVISIBLE);

                }
                else {
                    info1.setVisibility(View.VISIBLE);
                    info2.setVisibility(View.VISIBLE);
                    info3.setVisibility(View.VISIBLE);
                    info4.setVisibility(View.VISIBLE);
                    info5.setVisibility(View.VISIBLE);

                }

            }
        });

        Special.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SpecialT.requestFocus();
            }
        });


        // Vis tider i numberpicker
        String[] numbers = getNumbers();
        np.setDisplayedValues(numbers);
        np.setMaxValue(numbers.length - 1);
        np.setMinValue(0);


        // aflæs hvilke runder der er valgt, aflæs den valgte tid og vis Sendnames
        Button next = (Button) findViewById(R.id.BTN_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> rounds =new ArrayList<String>();

                if (FreeS.isChecked()==true)
                    rounds.add(FreeS.getText().toString());
                if (Mine.isChecked()==true)
                    rounds.add(Mine.getText().toString());
                if(oWord.isChecked()==true)
                    rounds.add(oWord.getText().toString());
                if (Special.isChecked()==true)
                    rounds.add(SpecialT.getText().toString());
                i.putStringArrayListExtra("RoundType", rounds);
                logic.setRoundTime(np.getValue());
                i.putExtra("RoundTime",logic.getRoundTime() );
                i.putExtra("isCreator", true);
                i.putExtra("gameName", gameName);
                startActivity(i);
            }

        });

        //Fokus på edittext når special round checkes af
        SRound.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                SRound.setText("");
                imm.showSoftInput(SpecialT, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }

        //sæt tilgængelige værdier i numberpickeren
        public String[] getNumbers()
        {
            String[] numbers = new String[180/5];
            Integer time;
            for (int i=0; i<numbers.length; i++)
            {
                time = (i * 5 + 5);
                if (time<60)
                {
                    numbers[i] = Integer.toString(time);
                }
                else if ( time <117)
                {
                    if(time<67)
                        numbers[i] = "1:0" + Integer.toString(time - 60);
                    else
                        numbers[i] = "1:" + Integer.toString(time - 60);
                }
                else if (time<177)
                {
                    if(time<127)
                        numbers[i] = "2:0"+Integer.toString(time-120);
                    else
                        numbers[i] = "2:" + Integer.toString(time - 120);
                }
                else
                {
                    if(time<187)
                        numbers[i] = "3:0"+Integer.toString(time-180);
                    else
                        numbers[i] = "3:" + Integer.toString(time - 180);
                }
            }

            return numbers;
        }


    //Når tilbageknappen trykkes vises der altid tilbagetil frontskærmen
    @Override
    public void onBackPressed()
    {
        Intent iBack = new Intent(this, CreateGame.class);
        startActivity(iBack);

    }





    }

