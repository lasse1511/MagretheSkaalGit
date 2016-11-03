package com.example.lasse.magretheskaal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 31-10-2016.
 */

public class LogicLayer /* implements Parcelable */ {

    int RoundTime;
    ArrayList<String> RoundType;
    ArrayList<String> NamesOrg;
    ArrayList<String> NamesEdit;
    public int Team1Score;
    public int Team2Score;

    public LogicLayer() {
        NamesOrg = new ArrayList<String>();
        NamesEdit = NamesOrg;
        Team1Score = 0;
        Team2Score = 0;
    }


    public void addToNames(String name) {
        NamesOrg.add(name);
    }

    public int displayNumberOfNames() {
        return NamesOrg.size();
    }


    public int setRoundTime(int rt) {
        return rt / 5 - 5;
    }

    public int getRoundTime() {
        return RoundTime;
    }

    public void setRoundType(List<String> rounds) {

    }

    public void team1Scores() {
        Team1Score++;
    }

    public void team2Scores() {
        Team2Score++;
    }

    public void removeName(int i) {
        NamesEdit.remove(i);
    }

/*
    public LogicLayer(Parcel parcel) {
        this.RoundTime = parcel.readInt();
        this.RoundType = parcel.readArrayList(null);
        this.NamesOrg = parcel.readArrayList(null);
        this.NamesEdit = parcel.readArrayList(null);
        this.Team1Score = parcel.readInt();
        this.Team2Score = parcel.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(RoundTime);
        dest.writeList(RoundType);
        dest.writeList(NamesOrg);
        dest.writeList(NamesEdit);
        dest.writeInt(Team1Score);
        dest.writeInt(Team2Score);
    }

    // Method to recreate a Question from a Parcel
    public static Creator<LogicLayer> CREATOR = new Creator<LogicLayer>() {

        @Override
        public LogicLayer createFromParcel(Parcel source) {
            return new LogicLayer(source);
        }

        @Override
        public LogicLayer[] newArray(int size) {
            return new LogicLayer[size];
        }


    };
    */


}