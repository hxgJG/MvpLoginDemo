package com.example.hexg.mvplogindemo.model;

import android.content.Context;

/**
 * Created by HEXG on 2017/11/8.
 */

public interface IUserModel {
    void login(Context context,String username, String pwd, LoginListener listener);
    void register(String username, String pwd, String pwdConfirm, RegisterListener listener);
}
