package com.example.administrator.newstest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newstest.Fragment.cJokeFragment;
import com.example.administrator.newstest.Fragment.cNewsFragment;
import com.example.administrator.newstest.Fragment.cStateFragment;
import com.example.administrator.newstest.Fragment.cWeatherFragment;
import com.example.administrator.newstest.R;
import com.example.administrator.newstest.adapter.MyContentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/28.
 */

public class MainContentActivity extends FragmentActivity {
    private List<Fragment> mFragmentList=new ArrayList<>();
    private ViewPager mViewPager;
    private ImageView ivUser;
    private TextView tvContentName;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private MyContentAdapter mMyContentAdapter;
    private View headerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maincontent);

        initViews();
        initNavigation();
        initFragment();
        initHeaderView();

        mMyContentAdapter=new MyContentAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mMyContentAdapter);
        mViewPager.setOffscreenPageLimit(4);
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

    private void initViews() {
        ivUser= (ImageView) findViewById(R.id.iv_User);
        tvContentName= (TextView) findViewById(R.id.tv_content);
        mNavigationView= (NavigationView) findViewById(R.id.nv_navigation);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.DrawerLayout);
        mViewPager= (ViewPager) findViewById(R.id.vp_viewpager);

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

                switch (itemId){
                    case R.id.menu_data:
                        Toast.makeText(MainContentActivity.this, "我的资料", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.menu_save:
                        Toast.makeText(MainContentActivity.this, "我的收藏", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.menu_speak:
                        Toast.makeText(MainContentActivity.this, "我的评论", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.menu_unRegiter:
                        Toast.makeText(MainContentActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;

                    default:
                        Toast.makeText(MainContentActivity.this, "出现错误", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

    }


    private void initHeaderView() {
        headerView=mNavigationView.getHeaderView(0);
        ImageView headImg= (ImageView) headerView.findViewById(R.id.iv_Myhead);
        TextView tvReg= (TextView) headerView.findViewById(R.id.tv_register);

        headImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainContentActivity.this,RegisterActivity.class));
            }
        });

    }


}
