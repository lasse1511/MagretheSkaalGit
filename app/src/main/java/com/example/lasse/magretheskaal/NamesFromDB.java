package com.example.lasse.magretheskaal;


public class NamesFromDB {

    //private attributes
    int _id;
    String _name;
    String _phone_number;

    // Empty constructor
    public NamesFromDB(){

    }
    // constructor
    public NamesFromDB(int id, String name){
        this._id = id;
        this._name = name;
    }

    // constructor
    public NamesFromDB(String name){
        this._name = name;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }
}
