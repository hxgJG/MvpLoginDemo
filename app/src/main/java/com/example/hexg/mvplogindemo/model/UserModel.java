package com.example.hexg.mvplogindemo.model;

import com.example.hexg.mvplogindemo.bean.User;

/**
 * Created by HEXG on 2017/11/8.
 */

public class UserModel implements IUserModel {
    @Override
    public void login(final String username, final String pwd, final LoginListener listener) {
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if ("hexg".equals(username) && "123".equals(pwd)){
                    User user = new User();
                    user.setUsername(username);
                    user.setPwd(pwd);
                    listener.loginSucceed(user);
                }
                else {
                    listener.loginFailed();
                }
            }
        }.start();
    }
}
