package com.account.accountapplication.accountuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.account.accountapplication.R;
import com.account.accountapplication.account.AccountActivity;
import com.account.accountapplication.data.my.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AccountUserActivity extends AppCompatActivity implements AccountUserContract.AccountUserView {

    private static AccountUserActivity accountUserActivity;
    AccountUserContract.AccountUserPresenter accountUserPresenter;

    public AccountUserActivity() {
        accountUserActivity = this;
    }

    public static AccountUserActivity getInstance() {
        return accountUserActivity;
    }

    private long accountId;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_user);
        accountUserPresenter = new AccountUserPresenterImpl(this);

        Intent intent = getIntent();
        accountId = intent.getLongExtra("accountId", -1L);

        recyclerView = findViewById(R.id.account_user_recycler_view);
        FloatingActionButton addButton = findViewById(R.id.add_account_user);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择
            }
        });
        TitleBar titleBar = findViewById(R.id.account_user_title);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountActivity accountActivity = AccountActivity.getInstance();
                accountActivity.getAccountLine(accountId);
                finish();
            }
        });
        getAccountUserList(accountId);
        getUserList();
    }

    public void getAccountUserList(long accountId){
        accountUserPresenter.getAccountUserList(accountId);
    }

    public void deleteAccountUser(User user) {
        user.setAccountId(accountId);
        accountUserPresenter.deleteAccountUser(user);
    }

    public void getUserList(){
        accountUserPresenter.getUserList();
    }

    public void addAccountUser(User user){
        user.setAccountId(accountId);
        accountUserPresenter.addAccountUser(user);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAccountUserList(List<User> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        AccountUserAdapter adapter = new AccountUserAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getUserListSuccess(List<User> list) {

    }
}
