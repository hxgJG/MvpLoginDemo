package com.example.hexg.mvplogindemo.util;

import android.content.Context;
import android.os.Looper;

import com.example.hexg.mvplogindemo.widget.ProgressDialog;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by HEXG on 2017/8/24.
 */

public class DialogUtil {

    private static ProgressDialog mDialog;
    private static Timer timer;

    /**
     * 显示有时间限制的Dialog，时间超过15s后Dialog自动消失
     * @param context 上下文
     * @param msg Dialog上显示的提示
     * @param errorMsg 时间超过15s后给出的错误提示
     * @param isShowCancle Dialog右上角是否显示取消标志，点击取消Dialog消失
     */
    public static void showDialog(final Context context, final String msg, final String errorMsg,
                                  final boolean isShowCancle) {
        try {
            if (mDialog == null) {
                mDialog = new ProgressDialog(context, msg, false, null);
            }
            mDialog.isShowCancle(isShowCancle);
            mDialog.show();
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Looper.prepare();
                    T.showLong(context, errorMsg);
                    dismissDialog();
                    Looper.loop();
                }
            }, 10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏有时间限制的Dialog
     */
    public static void dismissDialog() {
        try {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            if (mDialog != null) {
                if (mDialog.isShowing()) {
                    mDialog.dismiss();
                }
                mDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示没有时间限制的Dialog
     * @param context 上下文
     * @param msg Dialog上显示的提示
     * @param isShowCancle Dialog右上角是否显示取消标志，点击取消Dialog消失
     */
    public static void showDialogNoTime(final Context context, final String msg, final boolean isShowCancle) {
        try {
            if (mDialog == null) {
                mDialog = new ProgressDialog(context, msg, false, null);
            }
            mDialog.isShowCancle(isShowCancle);
            mDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏没有时间限制的Dialog
     */
    public static void dismissDialogNoTime() {
        try {
            if (mDialog != null) {
                if (mDialog.isShowing()) {
                    mDialog.dismiss();
                }
                mDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
