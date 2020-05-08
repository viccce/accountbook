package com.account.accountapplication.account;

import com.account.accountapplication.data.account.AccountModel;
import com.account.accountapplication.data.account.AccountModelImpl;

public class AccountPresenterImpl implements AccountContract.AccountPresenter, AccountModel.FinishListener{

    private AccountContract.AccountView accountView;
    private AccountModel accountModel;
    
    public AccountPresenterImpl(AccountContract.AccountView accountView){
        this.accountView = accountView;
        this.accountModel = new AccountModelImpl();
    }
}
