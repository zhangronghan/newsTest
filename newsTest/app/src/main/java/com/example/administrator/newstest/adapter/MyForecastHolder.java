package com.example.administrator.newstest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.newstest.R;

/**
 * Created by Administrator on 2017/5/30.
 */
public class MyForecastHolder extends RecyclerView.ViewHolder{
    public TextView tvTime;
    public TextView tvForecast;
    public TextView tvMax;
    public TextView tvMin;

    public MyForecastHolder(View itemView) {
        super(itemView);

        tvTime= (TextView) itemView.findViewById(R.id.tv_time);
        tvForecast= (TextView) itemView.findViewById(R.id.tv_forecast);
        tvMax= (TextView) itemView.findViewById(R.id.tv_max);
        tvMin= (TextView) itemView.findViewById(R.id.tv_min);
    }
}
