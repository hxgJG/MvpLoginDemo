package com.example.hexg.mvplogindemo.view;

/**
 * Created by HEXG on 2017/11/8.
 */

public interface ILoginView {

    String getUsername();
    String getPdw();
    void clearUserName();
    void clearPwd();
    void showFailed();
    void toActivity();
    void showProgress();
    void hideProgress();
}
