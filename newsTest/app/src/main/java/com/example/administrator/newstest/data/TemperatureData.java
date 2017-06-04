package com.example.administrator.newstest.data;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/3.
 */

public class TemperatureData {
    private int highY;
    private int lowY;
    private Date mDate;

    public TemperatureData(int highY, int lowY, Date date) {
        this.highY = highY;
        this.lowY = lowY;
        mDate = date;
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

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }


}
