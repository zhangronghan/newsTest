package com.example.administrator.newstest.Utils;

import android.os.Bundle;

import com.example.administrator.newstest.Fragment.NewsFragment;
import com.example.administrator.newstest.data.ConstantData;

/**
 * Created by Administrator on 2017/5/26.
 */

public class FragmentUtil {

    public static NewsFragment getNewsFragment(int index){
        NewsFragment fragment=new NewsFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(ConstantData.NEWSTYLE,index);
        fragment.setArguments(bundle);
        return fragment;
    }


}
