package com.example.lasse.magretheskaal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.ArrayList;
import java.util.List;

public class SetupGame extends AppCompatActivity {

    LogicLayer logic = new LogicLayer();
    SendNames sendNames = new SendNames();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_game);
        final NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);

        Button info2 = (Button) findViewById(R.id.BTN_Info2);
        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SetupGame.this, Info2.class));

            }
        });



        String[] numbers = getNumbers();
        np.setDisplayedValues(numbers);

        np.setMaxValue(numbers.length - 1);
        np.setMinValue(0);
        np.setWrapSelectorWheel(false);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int newValue = newVal * 5 + 5;
            }
        });


        Button next = (Button) findViewById(R.id.BTN_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SetupGame.this, SendNames.class));
                logic.setRoundTime(np.getValue());
                logic.setRoundType(getRounds());
                sendNames.setLogic(logic);


            }

        });
    }
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

        public List<String> getRounds ()
        {
            List<String> rounds =new ArrayList<String>();
            final CheckBox Special = (CheckBox) findViewById(R.id.checkBox7);
            final CheckBox oWord = (CheckBox) findViewById(R.id.checkBox6);
            final CheckBox Mine = (CheckBox) findViewById(R.id.checkBox5);
            final CheckBox FreeS = (CheckBox) findViewById(R.id.checkBox2);
            EditText SpecialT = (EditText) findViewById(R.id.editText);

            if (Special.isChecked()==true)
                rounds.add(SpecialT.getText().toString());
            if(oWord.isChecked()==true)
                rounds.add(oWord.getText().toString());
            if (Mine.isChecked()==true)
                rounds.add(Mine.getText().toString());
            if (FreeS.isChecked()==true)
                rounds.add(FreeS.getText().toString());

            return  rounds;
        }



    }

