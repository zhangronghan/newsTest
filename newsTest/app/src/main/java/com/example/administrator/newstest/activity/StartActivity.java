package com.example.administrator.newstest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.newstest.R;
import com.example.administrator.newstest.data.ConstantData;

/**
 * Created by Administrator on 2017/5/22.
 */

public class StartActivity extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        handler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what== ConstantData.THREAD_CODE){
                    startActivity(new Intent(StartActivity.this,welcomeActivity.class));
                }
                return true;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                    Message message=new Message();
                    message.what=ConstantData.THREAD_CODE;
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
