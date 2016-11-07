package com.example.lasse.magretheskaal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 31-10-2016.
 */

public class LogicLayer /* implements Parcelable */ {

    public int RoundTime;
    ArrayList<String> RoundType;
    ArrayList<String> NamesOrg;
    ArrayList<String> NamesEdit;
    public int Team1Score;
    public int Team2Score;
    public int RoundCounter;
    public boolean TeamOnesRound;

    public LogicLayer() {
        NamesOrg = new ArrayList<String>();
        NamesEdit = NamesOrg;
        Team1Score = 0;
        Team2Score = 0;
        TeamOnesRound = true;
    }


    public void addToNames(String name) {
        NamesOrg.add(name);
    }

    public int displayNumberOfNames() {
        return NamesOrg.size();
    }


    public int setRoundTime(int rt) {
        if( rt== 0) {
            RoundTime=5;
            return RoundTime;
        }
        else {
            RoundTime = rt*5;
            return RoundTime;
        }
    }

    public int getRoundTime() {
        return RoundTime;
    }

    public void setRoundType(List<String> rounds) {

    }

    public void teamScores() {

        if (TeamOnesRound == true)
            Team1Score++;
        else
            Team2Score++;
    }


    public void removeName(int i) {
        NamesEdit.remove(i);
    }



}