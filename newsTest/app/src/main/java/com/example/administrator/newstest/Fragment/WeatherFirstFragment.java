package com.example.administrator.newstest.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.example.administrator.newstest.R;
import com.example.administrator.newstest.adapter.MyWeatherForecastAdapter;
import com.example.administrator.newstest.data.WeatherData;
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
 * Created by Administrator on 2017/5/29.
 */

public class WeatherFirstFragment extends Fragment {
    private static final int FINISH_RESPONSE = 1001;
    private TextView tvCity;        //城市名
    private ImageView ivPopup;
    private PopupMenu popup;
    private RecyclerView mRecyclerView;
    private TextView tvTemp;        //温度
    private TextView tvState;       //天气状况
    private TextView tvUpdateTime;  //更新时间
    private TextView tvAqi;          //AQI指数
    private TextView tvPm;           //PM2.5指数
    private TextView tvClotherDesc;     //穿衣指数
    private TextView tvXqDesc;       //舒适度指数
    private TextView tvTravelDesc;  //旅游
    private TextView tvLsDesc;             //紫外线指数
    private TextView tvColdDesc;           //感冒
    private MyWeatherForecastAdapter mAdapter;
    private final String url = "https://free-api.heweather.com/v5/weather?city=广州&key=3b064f7a3335458aaf72e19a4329967e";
    private String myUrl;
    private String myCity;
    private List<WeatherData.HeWeather5Bean> mBeanList = new ArrayList<>();
    private List<WeatherData.HeWeather5Bean.DailyForecastBean> mForeList = new ArrayList<>();
    private Handler mHandler;
    private boolean isSuccessRequest = true;     //成功得到json数据
    private LocationClient mLocationClient;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_first, container, false);
        mLocationClient = new LocationClient(view.getContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        initViews(view);
        init();


        mAdapter = new MyWeatherForecastAdapter(mForeList);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWeatherForecast();


    }

    private void getWeatherForecast() {
        OkHttpClient client = new OkHttpClient();
        Request request;
        if (myCity != null) {
            myUrl = getUrl(myCity);
            request = new Request.Builder().url(myUrl).build();
            Log.e("URL", myUrl);
        } else {
            request = new Request.Builder().url(url).build();
        }

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure", "出错了 " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String result = response.body().string();
                Log.d("onResponse", result);
                WeatherData myData = gson.fromJson(result, WeatherData.class);

                if (myData.getHeWeather5().get(0).getStatus().equals("ok")) {
                    mBeanList = myData.getHeWeather5();
                    mHandler.sendEmptyMessage(FINISH_RESPONSE);
                } else {
                    Log.e("AAA", "unknown city");
                }

            }
        });

    }

    private String getUrl(String myCity) {
        String Url = "https://free-api.heweather.com/v5/weather?city=" + myCity + "&key=3b064f7a3335458aaf72e19a4329967e";
        return Url;
    }

    private void requestLocation() {
        mLocationClient.start();
    }

    private void initPara() {
        tvAqi.setText(mBeanList.get(0).getAqi().getCity().getAqi());
        tvPm.setText(mBeanList.get(0).getAqi().getCity().getPm25());
        tvTemp.setText(mBeanList.get(0).getNow().getTmp());
        tvState.setText(mBeanList.get(0).getNow().getCond().getTxt());
        tvUpdateTime.setText(mBeanList.get(0).getBasic().getUpdate().getLoc());
        tvClotherDesc.setText("穿衣指数：" + mBeanList.get(0).getSuggestion().getDrsg().getTxt());
        tvXqDesc.setText("舒适度指数：" + mBeanList.get(0).getSuggestion().getComf().getTxt());
        tvTravelDesc.setText("旅游：" + mBeanList.get(0).getSuggestion().getTrav().getTxt());
        tvLsDesc.setText("紫外线指数：" + mBeanList.get(0).getSuggestion().getUv().getTxt());
        tvColdDesc.setText("感冒：" + mBeanList.get(0).getSuggestion().getFlu().getTxt());
        tvCity.setText(mBeanList.get(0).getBasic().getCity());

    }


    private void init() {

        ivPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup = new PopupMenu(getContext(), ivPopup);
                popup.getMenuInflater().inflate(R.menu.popup_menu_home, popup.getMenu());

                popup.show();
                initMenuItemClick();
            }
        });

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == FINISH_RESPONSE) {
                    initPara();
                    mAdapter.getData(mBeanList.get(0).getDaily_forecast());
                    Log.e("aaa", mBeanList.get(0).getDaily_forecast().get(0).getTmp().getMax());
                    return true;
                }
                return false;
            }
        });

    }

    private void initMenuItemClick() {
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.popup_Loc:
                        requestLocation();
                        Toast.makeText(getContext(), "定位"+myCity, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.popup_Cho:
                        initDialog();

                        break;
                }
                return true;
            }
        });

    }

    private void initDialog() {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.ic_dialog, null);
        new AlertDialog.Builder(getContext()).setTitle("请输入城市")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edtCon = (EditText) view.findViewById(R.id.edt_content);
                        myCity = edtCon.getText().toString();
                        myUrl = getUrl(myCity);
                        getWeatherForecast();
                        dialog.dismiss();

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    private void initViews(View view) {
        tvState = (TextView) view.findViewById(R.id.tv_weather_state);
        tvCity = (TextView) view.findViewById(R.id.tv_city);
        ivPopup = (ImageView) view.findViewById(R.id.iv_popup);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.weather_recyclerView);
        tvUpdateTime = (TextView) view.findViewById(R.id.update_time);
        tvTemp = (TextView) view.findViewById(R.id.tv_temp);
        tvAqi = (TextView) view.findViewById(R.id.tv_aqi);
        tvPm = (TextView) view.findViewById(R.id.tv_pm);
        tvClotherDesc = (TextView) view.findViewById(R.id.clothes_desc);
        tvColdDesc = (TextView) view.findViewById(R.id.cold_desc);
        tvXqDesc = (TextView) view.findViewById(R.id.xq_desc);
        tvLsDesc = (TextView) view.findViewById(R.id.ls_desc);
        tvTravelDesc = (TextView) view.findViewById(R.id.travel_desc);

    }


    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            String my=bdLocation.getCity().toString();
            Log.e("AAA", "位置" + my);
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

}
