package com.account.accountapplication.addaccount;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.account.accountapplication.R;
import com.account.accountapplication.record.RecordActivity;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddAccountActivity extends AppCompatActivity implements AddAccountContract.AddAccountView {

    private static AddAccountActivity addAccountActivity;

    public AddAccountActivity(){
        addAccountActivity = this;
    }

    public static AddAccountActivity getInstance(){
        return addAccountActivity;
    }

    private AddAccountContract.AddAccountPresenter addAccountPresenter;

    private TitleBar titleBar;
    private EditText accountNameText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        addAccountPresenter = new AddAccountPresenterImpl(this);

        titleBar = (TitleBar) findViewById(R.id.add_account_title);
        accountNameText = (EditText) findViewById(R.id.account_name_input);
        RoundButton saveButton = (RoundButton) findViewById(R.id.account_save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountName = accountNameText.getText().toString();
                addAccountPresenter.createAccount(accountName);
            }
        });
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void createSuccess() {
        RecordActivity recordActivity = RecordActivity.getInstance();
        recordActivity.refreshRecord();
        finish();
    }
}
