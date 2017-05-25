package com.example.administrator.newstest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.administrator.newstest.Listener.OnItemClickListener;
import com.example.administrator.newstest.R;
import com.example.administrator.newstest.data.NewsData;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class NewsAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener{
    private List<NewsData.ResultBean.DataBean> NewsList;
    private OnItemClickListener mOnItemClickListener=null;

    public NewsAdapter(List<NewsData.ResultBean.DataBean> NewsList){
        this.NewsList=NewsList;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener=listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewsData.ResultBean.DataBean dataBean=NewsList.get(position);
        holder.itemView.setTag(position);

        holder.tvTitle.setText(dataBean.getTitle());
        Glide.with(holder.ivHead.getContext())
                .load(dataBean.getThumbnail_pic_s())
                .into(holder.ivHead);

    }

    @Override
    public int getItemCount() {
        return NewsList.size();
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public void changeData(List<NewsData.ResultBean.DataBean> mNewList){
        this.NewsList=mNewList;
        notifyDataSetChanged();
    }


}
