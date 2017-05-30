package com.example.administrator.newstest.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newstest.R;
import com.example.administrator.newstest.adapter.MyWeatherViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2017/5/28.
 */

public class cWeatherFragment extends Fragment {
    private ViewPager mViewPager;
    private List<Fragment> mFragmentList=new ArrayList<>();
    private MyWeatherViewPagerAdapter mMyWeatherViewPagerAdapter;
    private CircleIndicator mCircleIndicator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("cWeatherFragment","onCreate");

        initFragmentList();


    }



    private void initFragmentList() {
        WeatherFirstFragment firstFragment=new WeatherFirstFragment();
        WeatherSecondFragment secondFragment=new WeatherSecondFragment();
        mFragmentList.add(firstFragment);
        mFragmentList.add(secondFragment);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_weather,container,false);
        mViewPager= (ViewPager)view.findViewById(R.id.vp_viewpager);
        mCircleIndicator= (CircleIndicator)view.findViewById(R.id.circleIndicator);

        mMyWeatherViewPagerAdapter=new MyWeatherViewPagerAdapter(getChildFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mMyWeatherViewPagerAdapter);
        mCircleIndicator.setViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(2);

        return view;
    }
}
