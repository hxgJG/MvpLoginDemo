package com.example.hexg.mvplogindemo.model;

import android.content.Context;

import com.example.hexg.mvplogindemo.bean.User;
import com.example.hexg.mvplogindemo.util.SPUtil;
import com.example.hexg.mvplogindemo.util.StringUtils;

/**
 * Created by HEXG on 2017/11/8.
 */

public class UserModel implements IUserModel {
    @Override
    public void login(final Context context, final String username, final String pwd, final LoginListener listener) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    User registerInfo = SPUtil.getRegisterInfo(context);
                    if (registerInfo == null) {
                        listener.loginFailed();
                        return;
                    }

                    if (registerInfo.getUsername().equals(username) && registerInfo.getPwd().equals(pwd)) {
                        User user = new User();
                        user.setUsername(username);
                        user.setPwd(pwd);
                        listener.loginSucceed(user);
                    } else {
                        listener.loginFailed();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void register(final String username, final String pwd, final String pwdConfirm, final RegisterListener listener) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);

                    if (StringUtils.isNullOrEmpty(username)) {
                        listener.usernameIsEmpty();
                        return;
                    }

                    if (StringUtils.isNullOrEmpty(pwd)) {
                        listener.pwdIsEmpty();
                        return;
                    }

                    if (StringUtils.isNullOrEmpty(pwdConfirm)) {
                        listener.pwdConfirmIsEmpty();
                        return;
                    }

                    if (!pwd.equals(pwdConfirm)) {
                        listener.pwdDifferent();
                        return;
                    }

                    User user = new User();
                    user.setUsername(username);
                    user.setPwd(pwd);
                    listener.registerSucceed(user);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    listener.registerFailed();
                }
            }
        }.start();
    }
}
