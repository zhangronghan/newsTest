package com.example.administrator.newstest.Fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newstest.R;
import com.example.administrator.newstest.Utils.FragmentUtil;
import com.example.administrator.newstest.adapter.MyViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/28.
 */

public class cNewsFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<NewsFragment> mNewFragmentList = new ArrayList<>();
    private MyViewPagerAdapter myAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTitles();
        initFragments();
    }

    private void initTitles() {
        mTitles.add("头条");
        mTitles.add("娱乐");
        mTitles.add("科技");
        mTitles.add("体育");
    }

    private void initFragments() {
        mNewFragmentList.add(FragmentUtil.getNewsFragment(1));
        mNewFragmentList.add(FragmentUtil.getNewsFragment(2));
        mNewFragmentList.add(FragmentUtil.getNewsFragment(3));
        mNewFragmentList.add(FragmentUtil.getNewsFragment(4));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mynews, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);

        if (myAdapter == null) {
            myAdapter = new MyViewPagerAdapter(getFragmentManager(), mTitles, mNewFragmentList);
        }
        mViewPager.setAdapter(myAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(4);

        return view;
    }


}
