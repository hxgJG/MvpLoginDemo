package com.example.hexg.mvplogindemo.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.hexg.mvplogindemo.bean.User;
import com.example.hexg.mvplogindemo.model.IUserModel;
import com.example.hexg.mvplogindemo.model.LoginListener;
import com.example.hexg.mvplogindemo.model.UserModel;
import com.example.hexg.mvplogindemo.util.DialogUtil;
import com.example.hexg.mvplogindemo.util.SPUtil;
import com.example.hexg.mvplogindemo.util.StringUtils;
import com.example.hexg.mvplogindemo.util.SystemUtil;
import com.example.hexg.mvplogindemo.view.ILoginView;

/**
 * Created by HEXG on 2017/11/8.
 */

public class LoginPresenter {

    private static final int LOGIN_SUCCEED = 100;
    private static final int LOGIN_FAILED = 101;

    IUserModel userModel = null;
    ILoginView iLoginView = null;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOGIN_SUCCEED:
                    DialogUtil.dismissDialog();
                    iLoginView.toActivity();
                    break;
                case LOGIN_FAILED:
                    DialogUtil.dismissDialog();
                    iLoginView.showFailed();
                    break;
            }
        }
    };

    public LoginPresenter(ILoginView iLoginView) {
        this.userModel = new UserModel();
        this.iLoginView = iLoginView;
    }

    public void login(final Context context, View view) {
        SystemUtil.hideKeyBoard(context, view);
        DialogUtil.showDialog(context, "正在登陆", "登录失败", false);
        String uname = null, pwd = null;
        User account = SPUtil.getAccount(context);
        if (view == null && account != null && !StringUtils.isNullOrEmpty(account.getUsername())
                && !StringUtils.isNullOrEmpty(account.getPwd())) {
            uname = account.getUsername();
            pwd = account.getPwd();
        } else {
            uname = iLoginView.getUsername();
            pwd = iLoginView.getPdw();
        }

        userModel.login(context,uname, pwd, new LoginListener() {
            @Override
            public void loginSucceed(User user) {
                SPUtil.saveAccount(context, user);
                handler.sendEmptyMessage(LOGIN_SUCCEED);
            }

            @Override
            public void loginFailed() {
                SPUtil.saveAccount(context, null);
                handler.sendEmptyMessage(LOGIN_FAILED);
            }
        });
    }

    public void register(){
        iLoginView.toRegister();
    }

    public void clear(Context context, View view) {
        SystemUtil.hideKeyBoard(context, view);
        iLoginView.clearUserName();
        iLoginView.clearPwd();
    }
}
