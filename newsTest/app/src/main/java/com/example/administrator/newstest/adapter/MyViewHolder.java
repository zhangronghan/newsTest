package com.example.administrator.newstest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.newstest.R;

/**
 * Created by Administrator on 2017/5/23.
 */
public class MyViewHolder extends RecyclerView.ViewHolder{
    public ImageView ivHead;
    public TextView tvTitle;

    public MyViewHolder(View itemView) {
        super(itemView);

        ivHead= (ImageView) itemView.findViewById(R.id.iv_head);
        tvTitle= (TextView) itemView.findViewById(R.id.tv_title);
    }
}
