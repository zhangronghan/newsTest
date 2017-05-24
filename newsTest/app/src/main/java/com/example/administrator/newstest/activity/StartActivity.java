package com.example.administrator.newstest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.newstest.R;
import com.example.administrator.newstest.Utils.FirstUtil;

/**
 * Created by Administrator on 2017/5/22.
 */

public class StartActivity extends AppCompatActivity {
    private Handler mHandler=new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if(FirstUtil.getFirstRun(StartActivity.this)){
            startActivity(new Intent(StartActivity.this,welcomeActivity.class));
            FirstUtil.setFirstRun(this,false);
        } else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(StartActivity.this,MainActivity.class));
                    finish();
                }
            },1000);

        }


    }
}
