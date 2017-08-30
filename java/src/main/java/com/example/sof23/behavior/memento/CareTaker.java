package com.example.sof23.behavior.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Longwj on 2017/8/30.
 */

public class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state){
        mementoList.add(state);
    }

    public Memento get(int index){
        return mementoList.get(index);
    }
}
