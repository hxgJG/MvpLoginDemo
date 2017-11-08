package com.example.hexg.mvplogindemo.presenter;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.hexg.mvplogindemo.bean.User;
import com.example.hexg.mvplogindemo.model.IUserModel;
import com.example.hexg.mvplogindemo.model.LoginListener;
import com.example.hexg.mvplogindemo.model.UserModel;
import com.example.hexg.mvplogindemo.view.ILoginView;

/**
 * Created by HEXG on 2017/11/8.
 */

public class LoginPresenter {

    private static final int LOGIN_SUCCEED = 100;
    private static final int LOGIN_FAILED = 101;

    IUserModel userModel = null;
    ILoginView iLoginView = null;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case LOGIN_SUCCEED:
                    iLoginView.hideProgress();
                    iLoginView.toActivity();
                    break;
                case LOGIN_FAILED:
                    iLoginView.hideProgress();
                    iLoginView.showFailed();
                    break;
            }
        }
    };

    public LoginPresenter(ILoginView iLoginView) {
        this.userModel = new UserModel();
        this.iLoginView = iLoginView;
    }

    public void login(){
        iLoginView.showProgress();
        userModel.login(iLoginView.getUsername(), iLoginView.getPdw(), new LoginListener() {
            @Override
            public void loginSucceed(User user) {
                handler.sendEmptyMessage(LOGIN_SUCCEED);
            }

            @Override
            public void loginFailed() {
                handler.sendEmptyMessage(LOGIN_FAILED);
            }
        });
    }

    public void clear(){
        iLoginView.clearUserName();
        iLoginView.clearPwd();
    }
}
