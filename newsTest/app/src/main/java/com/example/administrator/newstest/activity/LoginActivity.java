package com.example.administrator.newstest.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newstest.R;
import com.example.administrator.newstest.data.MyUser;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/5/26.
 */
public class LoginActivity extends AppCompatActivity{
    private EditText edtPhone;
    private EditText edtPass;
    private EditText edtCode;
    private TextView tvCode;
    private Button btnLog;
    private Handler mHandler=new Handler();
    private int num=60;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        init();
    }

    private void initViews() {
        edtPhone= (EditText) findViewById(R.id.edt_Phone);
        edtPass= (EditText) findViewById(R.id.edt_Pass);
        edtCode= (EditText) findViewById(R.id.edt_code);
        tvCode= (TextView) findViewById(R.id.tv_code);
        btnLog= (Button) findViewById(R.id.btn_Log);

    }

    private void init() {
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                if(num>0){
                    num--;
                    tvCode.setText(String.valueOf(num));
                    mHandler.postDelayed(this,1000);
                } else {
                    num=60;
                    tvCode.setClickable(true);
                    tvCode.setText("获取验证码");
                }

            }
        };
        tvCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCode.setClickable(false);
                final String phone=edtPhone.getText().toString();
                getCount(phone);
                mHandler.postDelayed(runnable,0);

            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BmobUser bmobUser=new BmobUser();
                String phone=edtPhone.getText().toString();
                String password=edtPass.getText().toString();
                bmobUser.setUsername(phone);
                bmobUser.setPassword(password);

                BmobSMS.verifySmsCode(phone, edtCode.getText().toString(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            bmobUser.signUp(new SaveListener<MyUser>() {
                                @Override
                                public void done(MyUser myUser, BmobException e) {
                                    if(e==null){
                                        Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                    }
                });




            }
        });


    }

    private void getCount(String phone) {
        BmobSMS.requestSMSCode(phone, "newsTest", new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                if(e==null){
                    Log.d("requestSMSCode","验证码发送成功");
                }
            }
        });
    }


}
