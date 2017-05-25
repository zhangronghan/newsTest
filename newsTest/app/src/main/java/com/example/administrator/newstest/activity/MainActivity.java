package com.example.administrator.newstest.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.newstest.Fragment.NewsFragment;
import com.example.administrator.newstest.R;
import com.example.administrator.newstest.adapter.MyViewPagerAdapter;
import com.example.administrator.newstest.data.ConstantData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ArrayList<String> mTitles=new ArrayList<>();
    private ArrayList<NewsFragment> mNewFragmentList=new ArrayList<>();
    private MyViewPagerAdapter myAdapter;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ImageView ivUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initTitle();
        initFragments();
        init();

        myAdapter=new MyViewPagerAdapter(getSupportFragmentManager(),mTitles,mNewFragmentList);
        mViewPager.setAdapter(myAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void initViews() {
        mTabLayout= (TabLayout) findViewById(R.id.tablayout);
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        mNavigationView= (NavigationView) findViewById(R.id.navigation);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.DrawerLayout);
        ivUser= (ImageView) findViewById(R.id.iv_user);

    }

    private void initTitle() {
        mTitles.add("头条");
        mTitles.add("娱乐");
        mTitles.add("科技");
        mTitles.add("体育");
    }


    private void init() {
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
                        Toast.makeText(MainActivity.this, "我的资料", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.menu_save:
                        Toast.makeText(MainActivity.this, "我的收藏", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.menu_speak:
                        Toast.makeText(MainActivity.this, "我的评论", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.menu_unRegiter:
                        Toast.makeText(MainActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;

                    default:
                        Toast.makeText(MainActivity.this, "出现错误", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                }

                return true;
            }
        });

    }


    private void initFragments() {
        NewsFragment f1=new NewsFragment();
        Bundle bunble1=new Bundle();
        bunble1.putInt(ConstantData.NEWSTYLE,1);
        f1.setArguments(bunble1);

        NewsFragment f2=new NewsFragment();
        Bundle bunble2=new Bundle();
        bunble2.putInt(ConstantData.NEWSTYLE,2);
        f2.setArguments(bunble2);

        NewsFragment f3=new NewsFragment();
        Bundle bunble3=new Bundle();
        bunble3.putInt(ConstantData.NEWSTYLE,3);
        f3.setArguments(bunble3);

        NewsFragment f4=new NewsFragment();
        Bundle bunble4=new Bundle();
        bunble4.putInt(ConstantData.NEWSTYLE,4);
        f4.setArguments(bunble4);

        mNewFragmentList.add(f1);
        mNewFragmentList.add(f2);
        mNewFragmentList.add(f3);
        mNewFragmentList.add(f4);

    }


}

