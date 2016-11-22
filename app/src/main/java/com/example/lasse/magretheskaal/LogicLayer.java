package com.example.lasse.magretheskaal;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LogicLayer /* implements Parcelable */ {

    public int RoundTime;
    ArrayList<String> RoundType;
    ArrayList<String> NamesOrg;
    ArrayList<String> NamesEdit;
    public int Team1Score;
    public int Team2Score;
    public int RoundCounter;
    public boolean TeamOnesRound;
    String gameName;
    Boolean isCreator;
    DBHelper db;

    public LogicLayer() {
        NamesOrg = new ArrayList<String>();
        NamesEdit = NamesOrg;
        Team1Score = 0;
        Team2Score = 0;
        TeamOnesRound = true;
        RoundCounter = 0;
    }

    public LogicLayer(String gname, Boolean iCreator,Context context) {
        NamesOrg = new ArrayList<String>();
        NamesEdit = NamesOrg;
        Team1Score = 0;
        Team2Score = 0;
        TeamOnesRound = true;
        RoundCounter = 0;
        gameName = gname;
        isCreator = iCreator;
        db = new DBHelper(context,gname);

    }

    public void CreateTable()
    {
        if(isCreator==true)
        {
            db.onCreate(db.DATABASE_NAME);
        }
    }

    public void addToNames(String name) {
        NamesOrg.add(name);
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addName(new NamesFromDB(name));
    }

    public ArrayList<String> getAllNames() {
    // Reading all contacts
    Log.d("Reading: ", "Reading all contacts..");
        return db.getAllNames();
    }


    public int displayNumberOfNames() {
        return db.getNamesCount();
    }


    public int setRoundTime(int rt) {
        if( rt== 0) {
            RoundTime=5+1;
            return RoundTime;
        }
        else {
            RoundTime = 1+5+rt*5;
            return RoundTime;
        }
    }

    public int getRoundTime() {
        return RoundTime;
    }

    public void setRoundType(List<String> rounds) {

    }

    public void teamScores() {

        if (RoundCounter % 2 == 0)
            Team1Score++;
        else
            Team2Score++;
    }


    public void removeName(int i) {
        NamesEdit.remove(i);
    }


}