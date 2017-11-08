package com.example.hexg.mvplogindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hexg.mvplogindemo.presenter.LoginPresenter;
import com.example.hexg.mvplogindemo.util.SystemUtil;
import com.example.hexg.mvplogindemo.view.ILoginView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    private EditText username, password;
    private View login_progress;
    LoginPresenter presenter = new LoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login_progress = findViewById(R.id.login_progress);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                presenter.login();
                break;
            case R.id.btn_clear:
                presenter.clear();
                break;
        }
        SystemUtil.hideKeyBoard(this, view);
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
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toActivity() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showProgress() {
        login_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        login_progress.setVisibility(View.GONE);
    }
}

