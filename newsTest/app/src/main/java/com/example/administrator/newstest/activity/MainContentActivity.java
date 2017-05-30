package com.example.administrator.newstest.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maincontent);

        initViews();
        initFragment();
        initBottombar();

        mMyContentAdapter=new MyContentAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mMyContentAdapter);
        mViewPager.setOffscreenPageLimit(4);

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
