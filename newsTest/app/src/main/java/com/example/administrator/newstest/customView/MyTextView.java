package com.example.administrator.newstest.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.newstest.data.TempPoint;
import com.example.administrator.newstest.data.TemperatureData;
import com.example.administrator.newstest.data.WeatherData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */

public class MyTextView extends TextView {
    private List<WeatherData.HeWeather5Bean.DailyForecastBean> mList;
    private List<TemperatureData> mDataList=new ArrayList<>();
    private List<TempPoint> mPointList=new ArrayList<>();
    private int radius=5;
    private Paint mPaint;
    private Path highPath;
    private Path lowPath;


    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e("AAA","MyTextView");
    }


    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void getDataList(List<WeatherData.HeWeather5Bean.DailyForecastBean> mList){
        this.mList=mList;
        init();
    }

    private void init() {
        for(int i=0;i<mList.size();i++) {
            int x = 50 + (150 * i);
            int highY = (40 - Integer.valueOf(mList.get(i).getTmp().getMax())) * 5;
            int lowY = (40 - Integer.valueOf(mList.get(i).getTmp().getMin())) * 8;
            TempPoint point = new TempPoint(highY, lowY, x);
            mPointList.add(point);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("AAA","onDraw");

        mPaint=new Paint();
        drawPoint(canvas);
        drawLine(canvas);
    }




    private void drawPoint(Canvas canvas) {
        for(int i=0;i<mPointList.size();i++){
            TempPoint point=mPointList.get(i);
            String max=mList.get(i).getTmp().getMax();
            String min=mList.get(i).getTmp().getMin();

            mPaint.setColor(Color.RED);
            canvas.drawCircle(point.getX(),point.getHighY(),radius,mPaint);
            canvas.drawText(max,0,max.length(),point.getX()-10,point.getHighY()-10,mPaint);

            mPaint.setColor(Color.GREEN);
            canvas.drawCircle(point.getX(),point.getLowY(),radius,mPaint);
            canvas.drawText(min,0,min.length(),point.getX()-10,point.getLowY()-10,mPaint);

        }
    }

    private void drawLine(Canvas canvas) {
        highPath=new Path();
        lowPath=new Path();
        for(int i=0;i<mPointList.size();i++){
            TempPoint point=mPointList.get(i);
            if(i==0){
                highPath.moveTo(point.getX(),point.getHighY());
                lowPath.moveTo(point.getX(),point.getLowY());
            } else {
                TempPoint point1=mPointList.get(i-1);
                highPath.quadTo(point1.getX(),point1.getHighY(),point.getX(),point.getHighY());
                lowPath.quadTo(point1.getX(),point1.getLowY(),point.getX(),point.getLowY());
            }
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(5);
            mPaint.setColor(Color.BLUE);
            canvas.drawPath(highPath,mPaint);

            mPaint.setColor(Color.GRAY);
            canvas.drawPath(lowPath,mPaint);
        }

    }


}
