package com.example.hexg.mvplogindemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.hexg.mvplogindemo.presenter.LoginPresenter;
import com.example.hexg.mvplogindemo.presenter.RegisterPresenter;
import com.example.hexg.mvplogindemo.util.SPUtil;
import com.example.hexg.mvplogindemo.util.T;
import com.example.hexg.mvplogindemo.view.ILoginView;
import com.example.hexg.mvplogindemo.view.IRegisterView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, IRegisterView {

    private EditText username, password, password_confirm;
    RegisterPresenter presenter = new RegisterPresenter(this);
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        context = this;
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        password_confirm = (EditText) findViewById(R.id.password_confirm);
        findViewById(R.id.btn_register).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                presenter.register(context, view);
                break;
            case R.id.btn_clear:
                presenter.clear(context, view);
                break;
        }
    }

    @Override
    public String getUsername() {
        return username.getText().toString();
    }

    @Override
    public String getPdw() {
        return password.getText().toString();
    }

    @Override
    public String getPdwConfirm() {
        return password_confirm.getText().toString();
    }

    @Override
    public void clearUserName() {
        username.setText("");
    }

    @Override
    public void clearPwd() {
        password.setText("");
    }

    @Override
    public void clearPwdConfirm() {
        password_confirm.setText("");
    }

    @Override
    public void showFailed() {
        T.showShort(this, "注册失败");
    }

    @Override
    public void toActivity() {
        T.showShort(context, "注册成功");
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void usernameIsEmpty() {
        T.showShort(context, "请输入账户名");
    }

    @Override
    public void pwdIsEmpty() {
        T.showShort(context, "请输入密码");
    }

    @Override
    public void pwdConfirmIsEmpty() {
        T.showShort(context, "请输入确认密码");
    }

    @Override
    public void pwdDifferent() {
        T.showShort(context, "两次密码不同");
    }
}

