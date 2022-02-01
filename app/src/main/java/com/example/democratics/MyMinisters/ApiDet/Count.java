package com.example.democratics.MyMinisters.ApiDet;

import java.util.ArrayList;

public class Count {

    private int count;
    private ArrayList<Mps> mps;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Mps> getMps() {
        return mps;
    }

    public void setMps(ArrayList<Mps> mps) {
        this.mps = mps;
    }

    public Count(int count, ArrayList<Mps> mps) {
        this.count = count;
        this.mps = mps;
    }

}
