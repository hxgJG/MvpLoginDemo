package com.example.hexg.mvplogindemo.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hexg.mvplogindemo.R;

/**
 * Created by HEXG on 2017/11/9.
 */

public class ProgressDialog extends AlertDialog {

    private String msg;
    private boolean isShowCancle = false;
    private ImageView iv_cancle;

    public ProgressDialog(Context context, String msg, boolean cancelable, OnCancelListener
            cancelListener) {
        super(context, cancelable, cancelListener);
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void isShowCancle(boolean isShowCancle) {
        this.isShowCancle = isShowCancle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.trans_dialog_window_bg);
        getWindow().setContentView(R.layout.dialog_transparent);
        ((TextView) findViewById(R.id.tvDialogMsg)).setText(msg);
        iv_cancle = (ImageView) findViewById(R.id.iv_cancle);
        if (isShowCancle){
            iv_cancle.setVisibility(View.VISIBLE);
        }else {
            iv_cancle.setVisibility(View.GONE);
        }
        iv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog.this.dismiss();
            }
        });
    }

}
