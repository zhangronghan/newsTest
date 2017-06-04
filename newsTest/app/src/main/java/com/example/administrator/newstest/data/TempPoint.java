package com.example.administrator.newstest.data;

/**
 * Created by Administrator on 2017/6/3.
 */

public class TempPoint {
    private int highY;
    private int lowY;
    private int x;

    public TempPoint(int highY, int lowY, int x) {
        this.highY = highY;
        this.lowY = lowY;
        this.x = x;
    }

    public int getHighY() {
        return highY;
    }

    public void setHighY(int highY) {
        this.highY = highY;
    }

    public int getLowY() {
        return lowY;
    }

    public void setLowY(int lowY) {
        this.lowY = lowY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }


}
