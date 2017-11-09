package com.example.hexg.mvplogindemo.model;

import com.example.hexg.mvplogindemo.bean.User;

/**
 * Created by HEXG on 2017/11/8.
 */

public interface RegisterListener {
    void registerSucceed(User user);
    void registerFailed();
    void usernameIsEmpty();
    void pwdIsEmpty();
    void pwdConfirmIsEmpty();
    void pwdDifferent();
}
