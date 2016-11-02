package com.example.lasse.magretheskaal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 31-10-2016.
 */

public class LogicLayer {

    List<String> NamesOrg = new ArrayList<String>();
    List<String> Rounds = new ArrayList<String>();
    int RoundTime ;


    public void addToNames(String name){
        NamesOrg.add(name);
    }

    public int displayNumberOfNames(){
        return NamesOrg.size();
    }

    public  void setRoundTime(int rt)
    {
        RoundTime = rt/5-5;

    }
    public int getRoundTime()
    {
        return RoundTime;
    }

    public void setRoundType(List<String> rounds)
    {

    }

    

}