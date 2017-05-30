package com.example.administrator.newstest.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.newstest.R;
import com.example.administrator.newstest.Utils.FragmentUtil;
import com.example.administrator.newstest.Utils.NavigationUtil;
import com.example.administrator.newstest.activity.RegisterActivity;
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
    private ImageView ivUser;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private View headerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("AAA","onCreate");
        initTitles();
        initFragments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("AAA","onCreateView");
        View view = inflater.inflate(R.layout.fragment_mynews, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        ivUser= (ImageView) view.findViewById(R.id.iv_MyUser);
        mDrawerLayout= (DrawerLayout) view.findViewById(R.id.DrawerLayout);
        mNavigationView= (NavigationView) view.findViewById(R.id.nv_navigation);

        if (myAdapter == null) {
            myAdapter = new MyViewPagerAdapter(getFragmentManager(), mTitles, mNewFragmentList);
        }
        mViewPager.setAdapter(myAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(4);

        initNavigation();
        initHeaderView();


        return view;
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

    private void initNavigation() {
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId=item.getItemId();
                NavigationUtil.clickItemState(getContext(),itemId,mDrawerLayout);
                return true;
            }
        });
    }


    private void initHeaderView() {
        headerView=mNavigationView.getHeaderView(0);
        ImageView headImg= (ImageView) headerView.findViewById(R.id.iv_Myhead);

        headImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),RegisterActivity.class));
            }
        });

    }



}
