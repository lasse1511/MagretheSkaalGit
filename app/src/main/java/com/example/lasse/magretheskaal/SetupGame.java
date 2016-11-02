package com.example.lasse.magretheskaal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class SetupGame extends AppCompatActivity {

    LogicLayer logic = new LogicLayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_game);

        Button info2 = (Button) findViewById(R.id.BTN_Info2);

        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SetupGame.this, Info2.class));
            }
        });


        NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);

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

    public Integer getTime(int i)
    {
        return  i/5-5;
    }

    }

