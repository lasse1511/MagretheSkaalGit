package com.example.lasse.magretheskaal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 31-10-2016.
 */

public class LogicLayer {

    List<String> NamesOrg = new ArrayList<String>();

    public void addToNames(String name){
        NamesOrg.add(name);
    }

    public int displayNumberOfNames(){
        return NamesOrg.size();
    }

    

}
