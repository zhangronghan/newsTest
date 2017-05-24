package com.example.administrator.newstest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.newstest.Fragment.NewsFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/23.
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<String> mTitles;
    private ArrayList<NewsFragment> mFragmentArrayList;

    public MyViewPagerAdapter(FragmentManager fm,ArrayList<String> mTitles,ArrayList<NewsFragment> mFragmentArrayList) {
        super(fm);
        this.mTitles=mTitles;
        this.mFragmentArrayList=mFragmentArrayList;

    }


    @Override
    public Fragment getItem(int position) {
        if(position < mFragmentArrayList.size()){
            return mFragmentArrayList.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if(mFragmentArrayList !=null){
            return mFragmentArrayList.size();
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(mTitles !=null && position < mFragmentArrayList.size()){
            return mTitles.get(position);
        }
        return "notitle";
    }
}
