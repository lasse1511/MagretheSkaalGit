package com.example.lasse.magretheskaal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 31-10-2016.
 */

public class LogicLayer {

    List<String> NamesOrg;
    List<String> NamesEdit;
    public int Team1Score;
    public int Team2Score;

    public LogicLayer()
    {
        NamesOrg = new ArrayList<String>();
        NamesEdit = NamesOrg;
        Team1Score = 0;
        Team2Score = 0;
    }




    public void addToNames(String name){
        NamesOrg.add(name);
    }

    public int displayNumberOfNames(){
        return NamesOrg.size();
    }

    public void team1Scores()
    {
        Team1Score++;
    }

    public void team2Scores()
    {
        Team2Score++;
    }

    public void removeName(int i)
    {
        NamesEdit.remove(i);
    }
    

}
