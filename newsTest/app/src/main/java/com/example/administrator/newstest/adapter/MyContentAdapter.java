package com.example.administrator.newstest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/5/28.
 */

public class MyContentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    public MyContentAdapter(FragmentManager fm,List<Fragment> mFragmentList) {
        super(fm);
        this.mFragmentList=mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
