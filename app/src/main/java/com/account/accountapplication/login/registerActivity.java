package com.account.accountapplication.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.account.accountapplication.R;

import org.apache.commons.lang3.StringUtils;

public class registerActivity extends Activity implements RegisterContract.RegisterView, View.OnClickListener{

    private EditText username;
    private EditText password;
    private EditText email;
    private EditText mobile;
    private RegisterContract.RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.mobile);
        presenter = new RegisterPresenterImpl(this);
        findViewById(R.id.registers_button).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (!StringUtils.isEmpty(username.getText().toString())
                && !StringUtils.isEmpty(password.getText().toString())&& !StringUtils.isEmpty(email.getText().toString())) {
            Log.e("WangJ", "都不空");
            presenter.validateCredentials(username.getText().toString(), password.getText().toString(),email.getText().toString(), mobile.getText().toString());
        } else {
            Toast.makeText(registerActivity.this, "账号、密码、邮箱都不能为空！", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setUsernameError() {

    }

    @Override
    public void setPasswordError() {

    }

    @Override
    public void setEmailError() {

    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent();
        intent.setClass(registerActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
