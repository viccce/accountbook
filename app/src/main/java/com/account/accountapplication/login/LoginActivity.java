package com.account.accountapplication.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.account.accountapplication.R;
import com.account.accountapplication.record.RecordActivity;

import org.apache.commons.lang3.StringUtils;


public class LoginActivity extends Activity implements LoginContract.LoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private LoginContract.LoginPresenter presenter;
    private static LoginActivity loginActivity;
    public LoginActivity() {
        loginActivity = this;
    }
    public static LoginActivity getLoginActivity() {
        return loginActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
                intent.setClass(LoginActivity.this, registerActivity.class);

                startActivity(intent);
            }
        });
        presenter = new LoginPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        if (!StringUtils.isEmpty(username.getText().toString())
                && !StringUtils.isEmpty(password.getText().toString())) {
            Log.e("WangJ", "都不空");
            presenter.validateCredentials(username.getText().toString(), password.getText().toString());


       //     login(username.getText().toString(), password.getText().toString());


        } else {
            Toast.makeText(LoginActivity.this, "账号、密码都不能为空！", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToHome() {
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        intent.setClass(LoginActivity.getLoginActivity(), RecordActivity.class);
        LoginActivity.getLoginActivity().startActivity(intent);
        finish();
    }



}
