package com.example.hexg.mvplogindemo.view;

import com.example.hexg.mvplogindemo.bean.User;

/**
 * Created by HEXG on 2017/11/9.
 */

public interface IRegisterView {

    String getUsername();

    String getPdw();

    String getPdwConfirm();

    void clearUserName();

    void clearPwd();

    void clearPwdConfirm();

    void showFailed();

    void toActivity();

    void usernameIsEmpty();
    void pwdIsEmpty();
    void pwdConfirmIsEmpty();
    void pwdDifferent();
}
