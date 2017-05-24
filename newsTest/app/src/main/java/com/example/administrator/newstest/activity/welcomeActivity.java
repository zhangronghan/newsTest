package com.example.administrator.newstest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.administrator.newstest.R;

/**
 * Created by Administrator on 2017/5/22.
 */

public class welcomeActivity extends AppCompatActivity {
    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;
    private GestureDetectorListener mGestureDetectorListener;
    private TextView tvTouch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mViewFlipper= (ViewFlipper) findViewById(R.id.viewFlipper);
        tvTouch= (TextView) findViewById(R.id.tv_touch);
        mGestureDetectorListener=new GestureDetectorListener();
        mGestureDetector=new GestureDetector(this,mGestureDetectorListener);

        tvTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(welcomeActivity.this,MainActivity.class));
            }
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    class GestureDetectorListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX()>e2.getX()){
                mViewFlipper.setInAnimation(welcomeActivity.this,R.anim.push_left_in);
                mViewFlipper.setOutAnimation(welcomeActivity.this,R.anim.push_left_out);
                mViewFlipper.showNext();

                return true;
            }
            if(e1.getX()<e2.getX()){
                mViewFlipper.setInAnimation(welcomeActivity.this,R.anim.push_right_in);
                mViewFlipper.setOutAnimation(welcomeActivity.this,R.anim.push_right_out);
                mViewFlipper.showPrevious();

                return true;
            }
            return true;
        }
    }

}
