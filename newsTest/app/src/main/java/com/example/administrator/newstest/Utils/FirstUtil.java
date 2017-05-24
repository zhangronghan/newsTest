package com.example.administrator.newstest.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/5/23.
 */

public class FirstUtil {
    private static final String FIRST_SP="first_sp";
    private static final String FIRST_RUN="first_run";

    public static boolean getFirstRun(Context context){
        SharedPreferences sp=context.getSharedPreferences(FIRST_SP,Context.MODE_PRIVATE);
        return sp.getBoolean(FIRST_RUN,true);
    }

    public static void setFirstRun(Context context,boolean isFirst){
        SharedPreferences sp=context.getSharedPreferences(FIRST_SP,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean(FIRST_RUN,isFirst);
        editor.commit();

    }

}
