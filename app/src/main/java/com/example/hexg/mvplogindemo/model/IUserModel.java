package com.example.hexg.mvplogindemo.model;

/**
 * Created by HEXG on 2017/11/8.
 */

public interface IUserModel {
    void login(String username, String pwd, LoginListener listener);
}
