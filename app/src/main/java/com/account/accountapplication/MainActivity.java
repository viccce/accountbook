package com.account.accountapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.account.accountapplication.login.Constant;
import com.account.accountapplication.login.LoginContract;
import com.account.accountapplication.login.LoginPresenterImpl;
import com.account.accountapplication.login.registerActivity;
import com.account.accountapplication.record.recordActivity;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends Activity implements LoginContract.LoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private LoginContract.LoginPresenter presenter;
    private static MainActivity mainActivity;
    public MainActivity() {
        mainActivity = this;
    }
    public static MainActivity getMainActivity() {
        return mainActivity;
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
                intent.setClass(MainActivity.this, registerActivity.class);

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
            Toast.makeText(MainActivity.this, "账号、密码都不能为空！", Toast.LENGTH_SHORT).show();
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
    }



}
