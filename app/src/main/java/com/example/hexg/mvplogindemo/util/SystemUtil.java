package com.example.hexg.mvplogindemo.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by HEXG on 2017/11/8.
 */

public class SystemUtil {

    /**
     * 隐藏键盘
     * @param context
     * @param v
     */
    public static void hideKeyBoard(Context context,View v){
        if (v == null) return;
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
