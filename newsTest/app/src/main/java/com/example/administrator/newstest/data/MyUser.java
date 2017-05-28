package com.example.administrator.newstest.data;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/5/26.
 */

public class MyUser extends BmobUser{
    String phone;
    String Password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    @Override
    public void setPassword(String password) {
        Password = password;
    }
}
