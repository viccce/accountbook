package com.account.accountapplication.accountuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.account.accountapplication.R;
import com.account.accountapplication.account.AccountActivity;
import com.account.accountapplication.data.my.User;
import com.account.accountapplication.record.RecordActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private FloatingActionButton addButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_user);
        accountUserPresenter = new AccountUserPresenterImpl(this);

        Intent intent = getIntent();
        accountId = intent.getLongExtra("accountId", -1L);

        recyclerView = findViewById(R.id.account_user_recycler_view);
        addButton = findViewById(R.id.add_account_user);
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
                RecordActivity recordActivity = RecordActivity.getInstance();
                recordActivity.refreshRecord();
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
        String[] userNameList = new String[list.size()];
        final Map<String, Long> userMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++){
            userNameList[i] = list.get(i).getNickname();
            userMap.put(list.get(i).getNickname(), list.get(i).getId());
        }
        final String[] finalList = userNameList;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsPickerView pvOptions = new OptionsPickerBuilder(v.getContext(), new OnOptionsSelectListener() {
                    @Override
                    public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                        long userId = userMap.get(finalList[options1]);
                        User user = new User();
                        user.setId(userId);
                        user.setAccountId(accountId);
                        accountUserPresenter.addAccountUser(user);
                        return false;
                    }
                })
                        .setTitleText(getString(R.string.pay_type))
//                        .setSelectOptions(sexSelectOption)
                        .build();
                pvOptions.setPicker(finalList);
                pvOptions.show();
            }
        });
    }
}
