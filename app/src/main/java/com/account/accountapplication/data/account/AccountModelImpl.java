package com.account.accountapplication.data.account;

import com.account.accountapplication.account.AccountActivity;
import com.account.accountapplication.utils.VolleyUtil;

public class AccountModelImpl implements AccountModel {
    
    private VolleyUtil volleyUtil;
    
    public AccountModelImpl(){
        volleyUtil = VolleyUtil.getInstance(AccountActivity.getInstance());
    }
}
