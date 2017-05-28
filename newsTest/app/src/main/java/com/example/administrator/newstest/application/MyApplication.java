package com.example.administrator.newstest.application;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/5/22.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Bmob.initialize(this, "593468dde4afd0479ab5d207d40a3fd8");

    }
}
