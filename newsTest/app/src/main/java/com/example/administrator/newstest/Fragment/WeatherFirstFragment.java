package com.example.administrator.newstest.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newstest.R;

/**
 * Created by Administrator on 2017/5/29.
 */

public class WeatherFirstFragment extends Fragment {
    private TextView tvCity;        //城市名
    private ImageView ivPopup;
    private PopupMenu popup;
    private RecyclerView mRecyclerView;
    private TextView tvTemp;        //温度
    private TextView tvState;       //天气状况
    private TextView tvUpdateTime;  //更新时间
    private TextView tvAqi;          //AQI指数
    private TextView tvPm;           //PM2.5指数
    private TextView tvClotherDesc;     //穿衣指数
    private TextView tvXqDesc;       //心情指数
    private TextView tvTravelDesc;  //旅游
    private TextView tvLsDesc;             //晾晒指数
    private TextView tvColdDesc;           //感冒


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_weather_first,container,false);

        initViews(view);
        init();

        return view;
    }


    private void init() {

        ivPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup=new PopupMenu(getContext(),ivPopup);
                popup.getMenuInflater().inflate(R.menu.popup_menu_home,popup.getMenu());

                popup.show();
                initMenuItemClick();
            }
        });

    }

    private void initMenuItemClick() {
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.popup_Loc:
                        Toast.makeText(getContext(), "定位", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.popup_Cho:
                        Toast.makeText(getContext(), "选择城市", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

    }


    private void initViews(View view) {
        tvState= (TextView) view.findViewById(R.id.tv_weather_state);
        tvCity= (TextView) view.findViewById(R.id.tv_city);
        ivPopup= (ImageView) view.findViewById(R.id.iv_popup);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.weather_recyclerView);
        tvUpdateTime= (TextView) view.findViewById(R.id.update_time);
        tvTemp= (TextView) view.findViewById(R.id.tv_temp);
        tvAqi= (TextView) view.findViewById(R.id.tv_aqi);
        tvPm= (TextView) view.findViewById(R.id.tv_pm);
        tvClotherDesc= (TextView) view.findViewById(R.id.clothes_desc);
        tvColdDesc= (TextView) view.findViewById(R.id.cold_desc);
        tvXqDesc= (TextView) view.findViewById(R.id.xq_desc);
        tvLsDesc= (TextView) view.findViewById(R.id.ls_desc);
        tvTravelDesc= (TextView) view.findViewById(R.id.travel_desc);
    }


}
