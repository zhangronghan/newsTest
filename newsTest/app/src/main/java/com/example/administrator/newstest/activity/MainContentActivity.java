package com.example.administrator.newstest.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.administrator.newstest.Fragment.cJokeFragment;
import com.example.administrator.newstest.Fragment.cNewsFragment;
import com.example.administrator.newstest.Fragment.cStateFragment;
import com.example.administrator.newstest.Fragment.cWeatherFragment;
import com.example.administrator.newstest.MyViewPager;
import com.example.administrator.newstest.R;
import com.example.administrator.newstest.adapter.MyContentAdapter;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/28.
 */

public class MainContentActivity extends FragmentActivity{
    private List<Fragment> mFragmentList=new ArrayList<>();
    private MyViewPager mViewPager;
    private MyContentAdapter mMyContentAdapter;
    private BottomBar mBottomBar;

    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    private AMapLocationListener mLocationListener;
    //声明AMapLocationClientOption对象
    private AMapLocationClientOption mLocationOption = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maincontent);

        initViews();
        initFragment();
        initBottombar();
        initLocation();

        mLocationClient.startLocation();

        mMyContentAdapter=new MyContentAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mMyContentAdapter);
        mViewPager.setOffscreenPageLimit(4);

    }

    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                Log.e("Location", aMapLocation.getCity().toString());
            }
        };

        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption=new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }



    private void initViews() {
        mViewPager= (MyViewPager) findViewById(R.id.vp_viewpager);
        mBottomBar= (BottomBar) findViewById(R.id.bottombar);

    }

    private void initBottombar() {
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tab_news:
                        mViewPager.setCurrentItem(0);
                        break;

                    case R.id.tab_weather:
                        mViewPager.setCurrentItem(1);
                        break;

                    case R.id.tab_joke:
                        mViewPager.setCurrentItem(2);
                        break;

                    case R.id.tab_state:
                        mViewPager.setCurrentItem(3);
                        break;

                    default:
                        break;
                }


            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();

    }

    private void initFragment() {
        cNewsFragment cNews=new cNewsFragment();
        cJokeFragment cJoke=new cJokeFragment();
        cWeatherFragment cWeather=new cWeatherFragment();
        cStateFragment cState=new cStateFragment();

        mFragmentList.add(cNews);
        mFragmentList.add(cWeather);
        mFragmentList.add(cJoke);
        mFragmentList.add(cState);
    }


}
