package com.example.administrator.newstest.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newstest.R;

/**
 * Created by Administrator on 2017/5/29.
 */

public class WeatherFirstFragment extends Fragment {
    private TextView tvCity;
    private ImageView ivPopup;
    private PopupMenu popup;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_weather_first,container,false);
        tvCity= (TextView) view.findViewById(R.id.tv_city);
        ivPopup= (ImageView) view.findViewById(R.id.iv_popup);

        init();

        return view;
    }

    private void init() {

        ivPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup=new PopupMenu(getContext(),ivPopup);
                popup.getMenuInflater().inflate(R.menu.popup_menu_home,popup.getMenu());

                popup.show();
                initMenuItemClick();
            }
        });

    }

    private void initMenuItemClick() {
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.popup_Loc:
                        Toast.makeText(getContext(), "定位", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.popup_Cho:
                        Toast.makeText(getContext(), "选择城市", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

    }





}
