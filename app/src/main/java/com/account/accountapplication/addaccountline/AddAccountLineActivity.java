package com.account.accountapplication.addaccountline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.account.accountapplication.R;
import com.account.accountapplication.account.AccountActivity;
import com.account.accountapplication.record.RecordActivity;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

public class AddAccountLineActivity extends ComponentActivity implements AddAccountLineContract.AddAccountLineView {

    private static AddAccountLineActivity addAccountLineActivity;

    public AddAccountLineActivity() {
        addAccountLineActivity = this;
    }

    public static AddAccountLineActivity getInstance() {
        return addAccountLineActivity;
    }

    private AddAccountLineContract.AddAccountLinePresenter addAccountLinePresenter;

    private TextView account;
    private TextView accountType;
    private TextView payType;
    private EditText changeMoney;
    private String[] accountList;
    private String[] pickTypeList;
    private String[] payTypeList;
    long accountId;
    boolean isFastAdd = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_account_line);
        addAccountLinePresenter = new AddAccountLinePresenterImpl(this);
        Intent i = getIntent();
        accountId = i.getLongExtra("accountId", -1L);
        String accountName = i.getStringExtra("accountName");

        account = findViewById(R.id.account_in_account_line);
        accountType = findViewById(R.id.account_type);
        payType = findViewById(R.id.pay_type);
        changeMoney = findViewById(R.id.change_money);

        if(accountId != -1L){
            accountList = new String[]{accountName};
            account.setText(accountName);
        }else{
            isFastAdd = true;
            accountList = getAccountNameList();
        }


        pickTypeList = getResources().getStringArray(R.array.account_type_values);
        payTypeList = getResources().getStringArray(R.array.pay_type_values);

        TitleBar titleBar = findViewById(R.id.add_account_line_title);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RelativeLayout accountSelection = findViewById(R.id.account_select);

        accountSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsPickerView pvOptions = new OptionsPickerBuilder(v.getContext(), new OnOptionsSelectListener() {
                    @Override
                    public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                        account.setText(accountList[options1]);
                        accountId = getAccountIdByName(accountList[options1]);
                        return false;
                    }
                })
                        .setTitleText(getString(R.string.account_type))
                        .build();
                pvOptions.setPicker(accountList);
                pvOptions.show();
            }
        });
        RelativeLayout accountTypeSelection = findViewById(R.id.account_type_select);
        accountTypeSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsPickerView pvOptions = new OptionsPickerBuilder(v.getContext(), new OnOptionsSelectListener() {
                    @Override
                    public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                        accountType.setText(pickTypeList[options1]);
                        return false;
                    }
                })
                        .setTitleText(getString(R.string.account_type))
//                        .setSelectOptions(sexSelectOption)
                        .build();
                pvOptions.setPicker(pickTypeList);
                pvOptions.show();
            }
        });
        RelativeLayout payTypeSelection = findViewById(R.id.pay_type_select);
        payTypeSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsPickerView pvOptions = new OptionsPickerBuilder(v.getContext(), new OnOptionsSelectListener() {
                    @Override
                    public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                        payType.setText(payTypeList[options1]);
                        return false;
                    }
                })
                        .setTitleText(getString(R.string.pay_type))
//                        .setSelectOptions(sexSelectOption)
                        .build();
                pvOptions.setPicker(payTypeList);
                pvOptions.show();
            }
        });
        RoundButton saveButton = findViewById(R.id.save_account_line_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAccountLine();
            }
        });
    }

    void saveAccountLine(){
        String at = accountType.getText().toString();
        String pt = payType.getText().toString();
        String money = changeMoney.getText().toString();
        addAccountLinePresenter.saveAccountLine(accountId, at, money, pt);
    }

    String[] getAccountNameList(){
        return addAccountLinePresenter.getAccountNameList();
    }

    Long getAccountIdByName(String name){
        return addAccountLinePresenter.getAccountIdByName(name);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveSuccess() {
        if(isFastAdd){
            RecordActivity recordActivity = RecordActivity.getInstance();
            recordActivity.refreshRecord();
        }else{
            AccountActivity accountActivity = AccountActivity.getInstance();
            accountActivity.getAccountLine(accountId);
        }
        finish();
    }
}
