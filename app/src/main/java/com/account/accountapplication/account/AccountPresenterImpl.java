package com.account.accountapplication.account;

import com.account.accountapplication.data.account.AccountModel;
import com.account.accountapplication.data.account.AccountModelImpl;
import com.account.accountapplication.data.record.Account;
import com.account.accountapplication.data.record.AccountLine;

import java.util.List;

public class AccountPresenterImpl implements AccountContract.AccountPresenter, AccountModel.FinishListener{

    private AccountContract.AccountView accountView;
    private AccountModel accountModel;
    
    public AccountPresenterImpl(AccountContract.AccountView accountView){
        this.accountView = accountView;
        this.accountModel = new AccountModelImpl();
    }

    @Override
    public void getAccountLine(Long accountId) {
        if(accountId == null){
            accountView.onError("ERROR");
            return;
        }
        Account account = new Account();
        account.setAccountId(accountId);
        accountModel.getAccountLine(account, this);
    }

    @Override
    public void getAccountLineFinished(List<AccountLine> list) {
        accountView.getAccountLineSuccess(list);
    }

    @Override
    public void onError(String message) {
        accountView.onError(message);
    }
}
