package com.example.hexg.mvplogindemo.model;

import com.example.hexg.mvplogindemo.bean.User;

/**
 * Created by HEXG on 2017/11/8.
 */

public interface LoginListener {
    void loginSucceed(User user);
    void loginFailed();
}
