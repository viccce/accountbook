package com.account.accountapplication.account;

import android.os.Bundle;

import com.account.accountapplication.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {

    public static AccountActivity accountActivity;

    public AccountActivity(){
        accountActivity = this;
    }

    public static AccountActivity getInstance(){
        return accountActivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);
    }
}
