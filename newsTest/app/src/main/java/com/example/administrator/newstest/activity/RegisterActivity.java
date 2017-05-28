package com.example.administrator.newstest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newstest.R;
import com.example.administrator.newstest.data.MyUser;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by Administrator on 2017/5/26.
 */
public class RegisterActivity extends AppCompatActivity{
    private EditText edtName;
    private EditText edtPass;
    private Button btnReg;
    private TextView tvLog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        init();

    }

    private void initViews() {
        edtName= (EditText) findViewById(R.id.edt_username);
        edtPass= (EditText) findViewById(R.id.edt_password);
        btnReg= (Button) findViewById(R.id.btn_Reg);
        tvLog= (TextView) findViewById(R.id.tv_login);

    }

    private void init() {
        tvLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=edtName.getText().toString();
                String password=edtPass.getText().toString();
                BmobUser.loginByAccount(phone, password, new LogInListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if(myUser!=null){
                            Toast.makeText(RegisterActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                        }
                    }
                });


            }
        });


    }
}
