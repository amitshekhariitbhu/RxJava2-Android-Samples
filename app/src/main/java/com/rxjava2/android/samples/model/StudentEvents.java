package com.rxjava2.android.samples.model;

/**
 * Created by Jant on 2017/4/16.
 */

public class StudentEvents {

    private int mNumber;
    private String mName;


    public StudentEvents(int number, String name) {
        mNumber = number;
        mName = name;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int number) {
        mNumber = number;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
