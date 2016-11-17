package com.example.lasse.magretheskaal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.ArrayList;

public class SetupGame extends AppCompatActivity {

    private LogicLayer logic = new LogicLayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_game);

        //set diverse objekter på interfacet
        final NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);
        final EditText SRound = (EditText) findViewById(R.id.editText_SRound);
        final CheckBox Special = (CheckBox) findViewById(R.id.checkBox7);
        final CheckBox oWord = (CheckBox) findViewById(R.id.checkBox6);
        final CheckBox Mine = (CheckBox) findViewById(R.id.checkBox5);
        final CheckBox FreeS = (CheckBox) findViewById(R.id.checkBox2);
        final EditText SpecialT = (EditText) findViewById(R.id.editText_SRound);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);



        //Sæt standartval af rundetyper
        oWord.setChecked(true);
        Mine.setChecked(true);
        FreeS.setChecked(true);

        // vis infp
        Button info2 = (Button) findViewById(R.id.BTN_Info2);
        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SetupGame.this, Info2.class));
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
                Intent i = new Intent(SetupGame.this, SendNames.class);
                i.putStringArrayListExtra("RoundType", rounds);
                logic.setRoundTime(np.getValue());
                i.putExtra("RoundTime",logic.getRoundTime() );
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

       /* SpecialT.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            imm.showSoftInput(SpecialT, InputMethodManager.HIDE_IMPLICIT_ONLY);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    */


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

