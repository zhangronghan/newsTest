package com.example.administrator.newstest.Utils;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

import com.example.administrator.newstest.R;

/**
 * Created by Administrator on 2017/5/28.
 */

public class NavigationUtil {


    public static void clickItemState(Context context, int itemId, DrawerLayout mDrawerLayout) {

        switch (itemId){
            case R.id.menu_data:
                Toast.makeText(context, "我的资料", Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();
                break;

            case R.id.menu_save:
                Toast.makeText(context, "我的收藏", Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();
                break;

            case R.id.menu_speak:
                Toast.makeText(context, "我的评论", Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();
                break;

            case R.id.menu_unRegiter:
                Toast.makeText(context, "退出登录", Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();
                break;

            default:
                Toast.makeText(context, "出现错误", Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();
                break;
        }
    }


}
