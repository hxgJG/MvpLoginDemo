package com.example.hexg.mvplogindemo.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.hexg.mvplogindemo.bean.User;
import com.example.hexg.mvplogindemo.model.IUserModel;
import com.example.hexg.mvplogindemo.model.RegisterListener;
import com.example.hexg.mvplogindemo.model.UserModel;
import com.example.hexg.mvplogindemo.util.DialogUtil;
import com.example.hexg.mvplogindemo.util.SPUtil;
import com.example.hexg.mvplogindemo.util.StringUtils;
import com.example.hexg.mvplogindemo.util.SystemUtil;
import com.example.hexg.mvplogindemo.view.IRegisterView;

/**
 * Created by HEXG on 2017/11/8.
 */

public class RegisterPresenter {
    private static final int REGISTER_SUCCEED = 1;
    private static final int REGISTER_FAILED = 0;
    private static final int REGISTER_USERNAME_IS_EMPTY = -100;
    private static final int REGISTER_PWD_IS_EMPTY = -101;
    private static final int REGISTER_PWD_CONFIRM_IS_EMPTY = -102;
    private static final int REGISTER_PWD_DIFFERENT = -103;

    IUserModel userModel = null;
    IRegisterView iRegisterView = null;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REGISTER_SUCCEED:
                    DialogUtil.dismissDialog();
                    iRegisterView.toActivity();
                    break;
                case REGISTER_FAILED:
                    DialogUtil.dismissDialog();
                    iRegisterView.showFailed();
                    break;
                case REGISTER_USERNAME_IS_EMPTY:
                    DialogUtil.dismissDialog();
                    iRegisterView.usernameIsEmpty();
                    break;
                case REGISTER_PWD_IS_EMPTY:
                    DialogUtil.dismissDialog();
                    iRegisterView.pwdIsEmpty();
                    break;
                case REGISTER_PWD_CONFIRM_IS_EMPTY:
                    DialogUtil.dismissDialog();
                    iRegisterView.pwdConfirmIsEmpty();
                    break;
                case REGISTER_PWD_DIFFERENT:
                    DialogUtil.dismissDialog();
                    iRegisterView.pwdDifferent();
                    break;
            }
        }
    };

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.userModel = new UserModel();
        this.iRegisterView = iRegisterView;
    }

    public void register(final Context context, View view) {
        SystemUtil.hideKeyBoard(context, view);
        DialogUtil.showDialog(context, "正在注册", "注册失败", false);
        userModel.register(iRegisterView.getUsername(), iRegisterView.getPdw(), iRegisterView.getPdwConfirm(), new RegisterListener() {
            @Override
            public void registerSucceed(User user) {
                SPUtil.saveRegisterInfo(context, user);
                handler.sendEmptyMessage(REGISTER_SUCCEED);
            }

            @Override
            public void registerFailed() {
                SPUtil.saveAccount(context, null);
                handler.sendEmptyMessage(REGISTER_FAILED);
            }

            @Override
            public void usernameIsEmpty() {
                handler.sendEmptyMessage(REGISTER_USERNAME_IS_EMPTY);
            }

            @Override
            public void pwdIsEmpty() {
                handler.sendEmptyMessage(REGISTER_PWD_IS_EMPTY);
            }

            @Override
            public void pwdConfirmIsEmpty() {
                handler.sendEmptyMessage(REGISTER_PWD_CONFIRM_IS_EMPTY);
            }

            @Override
            public void pwdDifferent() {
                handler.sendEmptyMessage(REGISTER_PWD_DIFFERENT);
            }
        });
    }

    public void clear(Context context, View view) {
        SystemUtil.hideKeyBoard(context, view);
        iRegisterView.clearUserName();
        iRegisterView.clearPwd();
        iRegisterView.clearPwdConfirm();
    }
}
