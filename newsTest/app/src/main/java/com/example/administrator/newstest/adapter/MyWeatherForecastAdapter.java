package com.example.administrator.newstest.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newstest.R;
import com.example.administrator.newstest.data.WeatherData;

import java.util.List;

/**
 * Created by Administrator on 2017/5/30.
 */

public class MyWeatherForecastAdapter extends RecyclerView.Adapter<MyForecastHolder>{
    private List<WeatherData.HeWeather5Bean.DailyForecastBean> mBeanList;

    public MyWeatherForecastAdapter(List<WeatherData.HeWeather5Bean.DailyForecastBean> mBeanList){
        this.mBeanList=mBeanList;
    }

    @Override
    public MyForecastHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item,parent,false);
        MyForecastHolder myHolder=new MyForecastHolder(view);
        return myHolder;

    }


    @Override
    public void onBindViewHolder(MyForecastHolder holder, int position) {
        WeatherData.HeWeather5Bean.DailyForecastBean weatherForecasData=mBeanList.get(position);
        Log.e("BBB","111   "+weatherForecasData.getTmp().getMax());
        holder.tvTime.setText(weatherForecasData.getDate());
        holder.tvForecast.setText(weatherForecasData.getCond().getTxt_d());
        holder.tvMax.setText(weatherForecasData.getTmp().getMax());
        holder.tvMin.setText(weatherForecasData.getTmp().getMin());

    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    public void getData(List<WeatherData.HeWeather5Bean.DailyForecastBean> mBeanList){
        this.mBeanList=mBeanList;
        notifyDataSetChanged();
    }

}
