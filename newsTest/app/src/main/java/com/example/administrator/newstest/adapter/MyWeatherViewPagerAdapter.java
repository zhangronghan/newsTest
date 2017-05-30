package com.example.administrator.newstest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/5/30.
 */

public class MyWeatherViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    public MyWeatherViewPagerAdapter(FragmentManager fm,List<Fragment> mFragmentList) {
        super(fm);
        this.mFragmentList=mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        if(mFragmentList!=null){
            return mFragmentList.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if(mFragmentList!=null){
            return mFragmentList.size();
        }
        return 0;
    }
}
