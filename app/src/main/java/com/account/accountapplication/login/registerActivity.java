package com.account.accountapplication.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.account.accountapplication.MainActivity;
import com.account.accountapplication.R;
import com.account.accountapplication.record.recordActivity;

import org.apache.commons.lang3.StringUtils;

public class registerActivity extends Activity implements RegisterContract.RegisterView, View.OnClickListener{

    private EditText username;
    private EditText password;
    private EditText email;
    private RegisterContract.RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        presenter = new RegisterPresenterImpl(this);
        findViewById(R.id.registers_button).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (!StringUtils.isEmpty(username.getText().toString())
                && !StringUtils.isEmpty(password.getText().toString())&& !StringUtils.isEmpty(email.getText().toString())) {
            Log.e("WangJ", "都不空");
            presenter.validateCredentials(username.getText().toString(), password.getText().toString(),email.getText().toString());
            //     login(username.getText().toString(), password.getText().toString());

            Intent intent  = new Intent();
            intent.setClass(registerActivity.this, MainActivity.class);
            startActivity(intent);
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

    }
}
