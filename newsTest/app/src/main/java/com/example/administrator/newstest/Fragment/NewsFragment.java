package com.example.administrator.newstest.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newstest.R;
import com.example.administrator.newstest.adapter.NewsAdapter;
import com.example.administrator.newstest.data.ConstantData;
import com.example.administrator.newstest.data.NewsData;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/23.
 */

public class NewsFragment extends Fragment {
    private static final int MY_NEW_DATA = 1001;
    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;
    private List<NewsData.ResultBean.DataBean> mNewsList=new ArrayList<>();
    private Handler mHandler;
    private int newType;
    private String url1="http://v.juhe.cn/toutiao/index?type=top&key=be2c56bc5ca0b4e1a1a407f33ee954af";
    private String url2="http://v.juhe.cn/toutiao/index?type=yule&key=be2c56bc5ca0b4e1a1a407f33ee954af";
    private String url3="http://v.juhe.cn/toutiao/index?type=keji&key=be2c56bc5ca0b4e1a1a407f33ee954af";
    private String url4="http://v.juhe.cn/toutiao/index?type=tiyu&key=be2c56bc5ca0b4e1a1a407f33ee954af";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newType=getArguments().getInt(ConstantData.NEWSTYLE);

        initHandler();
        getNewsData();
    }

    private void initHandler() {
        mHandler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what==MY_NEW_DATA){
                    mNewsAdapter.changeData(mNewsList);
                    return true;
                }
                return false;
            }
        });

    }

    private void getNewsData() {
                OkHttpClient client=new OkHttpClient();

                Request request;

                switch (newType){
                    case 1:
                        request=new Request.Builder().url(url1).build();
                        break;

                    case 2:
                        request=new Request.Builder().url(url2).build();
                        break;

                    case 3:
                        request=new Request.Builder().url(url3).build();
                        break;

                    case 4:
                        request=new Request.Builder().url(url4).build();
                        break;

                    default:
                        request=new Request.Builder().url(url1).build();
                        break;

                }

                Call call=client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("NewsFragment","onFailure");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Gson gson=new Gson();
                        String result=response.body().string();
                        Log.e("AAA",result.toString());
                        NewsData newsData=gson.fromJson(result,NewsData.class);
                        mNewsList=newsData.getResult().getData();

                        mHandler.sendEmptyMessage(MY_NEW_DATA);
                    }
                });

     }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("BBB","onCreateView");
        View view=inflater.inflate(R.layout.fragment_news,container,false);

        mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        mNewsAdapter=new NewsAdapter(mNewsList);
        mRecyclerView.setAdapter(mNewsAdapter);

        LinearLayoutManager manager=new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(manager);

        return view;
    }


}




