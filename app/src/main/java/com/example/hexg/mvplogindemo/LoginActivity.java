package com.example.hexg.mvplogindemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hexg.mvplogindemo.presenter.LoginPresenter;
import com.example.hexg.mvplogindemo.util.SPUtil;
import com.example.hexg.mvplogindemo.util.T;
import com.example.hexg.mvplogindemo.view.ILoginView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    private EditText username, password;
    LoginPresenter presenter = new LoginPresenter(this);
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        initData();
    }

    private void initData() {
        if (SPUtil.hasAccountInfo(context)) {
            presenter.login(context, null);
        }
    }

    private void init() {
        context = this;
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.tv_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                presenter.login(context, view);
                break;
            case R.id.btn_clear:
                presenter.clear(context, view);
                break;
            case R.id.tv_register:
                presenter.register();
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
    public void clearUserName() {
        username.setText("");
    }

    @Override
    public void clearPwd() {
        password.setText("");
    }

    @Override
    public void showFailed() {
        T.showShort(this, "登录失败");
    }

    @Override
    public void toActivity() {
        T.showShort(context, "登录成功");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void toRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
}

