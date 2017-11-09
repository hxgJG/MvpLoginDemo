package com.example.hexg.mvplogindemo.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hexg.mvplogindemo.bean.User;
import com.example.hexg.mvplogindemo.constant.Constants;

/**
 * Created by HEXG on 2017/11/9.
 */

public class SPUtil {

    public static void saveAccount(Context context, User user) {
        String uname = null, pwd = null;
        boolean boo = false;
        if (user != null) {
            uname = user.getUsername();
            pwd = user.getPwd();
            boo = true;
        }
        SharedPreferences preference = context.getSharedPreferences(Constants.SP_ACCOUNT_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(Constants.ACCOUNT_NAME, uname);
        editor.putString(Constants.ACCOUNT_PWD, pwd);
        editor.putBoolean(Constants.HAS_ACCOUNT_INFO, boo);
        editor.apply();
    }

    public static void saveRegisterInfo(Context context, User user) {
        SharedPreferences preference = context.getSharedPreferences(Constants.SP_REGISTER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(Constants.ACCOUNT_NAME, user.getUsername());
        editor.putString(Constants.ACCOUNT_PWD, user.getPwd());
        editor.apply();
    }

    public static User getRegisterInfo(Context context){
        User user = new User();
        try {
            SharedPreferences preference = context.getSharedPreferences(Constants.SP_REGISTER_INFO, Context.MODE_PRIVATE);
            String usernmae = preference.getString(Constants.ACCOUNT_NAME, "");
            String pwd = preference.getString(Constants.ACCOUNT_PWD, "");
            user.setUsername(usernmae);
            user.setPwd(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User getAccount(Context context){
        User user = new User();
        try {
            SharedPreferences preference = context.getSharedPreferences(Constants.SP_ACCOUNT_INFO, Context.MODE_PRIVATE);
            String usernmae = preference.getString(Constants.ACCOUNT_NAME, "");
            String pwd = preference.getString(Constants.ACCOUNT_PWD, "");
            user.setUsername(usernmae);
            user.setPwd(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static boolean hasAccountInfo(Context context){
        boolean boo = false;
        try {
            SharedPreferences preference = context.getSharedPreferences(Constants.SP_ACCOUNT_INFO, Context.MODE_PRIVATE);
            boo = preference.getBoolean(Constants.HAS_ACCOUNT_INFO, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boo;
    }
}
