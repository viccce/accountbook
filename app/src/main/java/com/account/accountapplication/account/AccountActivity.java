package com.account.accountapplication.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.account.accountapplication.R;
import com.account.accountapplication.addaccountline.AddAccountLineActivity;
import com.account.accountapplication.data.record.AccountLine;
import com.account.accountapplication.record.RecordActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AccountActivity extends AppCompatActivity implements AccountContract.AccountView {

    public static AccountActivity accountActivity;

    public AccountActivity() {
        accountActivity = this;
    }

    public static AccountActivity getInstance() {
        return accountActivity;
    }

    private AccountContract.AccountPresenter accountPresenter;

    private RecyclerView recyclerView;

    @Override
    protected void onDestroy() {
        RecordActivity.getInstance().refreshRecord();
        super.onDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);
        accountPresenter = new AccountPresenterImpl(this);

        Intent i = getIntent();
        Long accountId = null;
        accountId = i.getLongExtra("accountId", -1L);
        final String accountName = i.getStringExtra("accountName");

        if (accountId == -1L) {
            finish();
        } else {
            recyclerView = findViewById(R.id.account_line_recycler_view);
            TitleBar titleBar = findViewById(R.id.account_line_title);
            titleBar.setLeftClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            FloatingActionButton addButton = findViewById(R.id.add_account_line);
            final Long finalAccountId = accountId;
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("accountId", finalAccountId);
                    intent.putExtra("accountName", accountName);
                    intent.setClass(AccountActivity.this, AddAccountLineActivity.class);
                    startActivity(intent);
                }
            });
            this.getAccountLine(accountId);
        }


    }

    public void getAccountLine(Long accountId) {
        accountPresenter.getAccountLine(accountId);
    }

    @Override
    public void getAccountLineSuccess(List<AccountLine> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        AccountLineAdapter adapter = new AccountLineAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
