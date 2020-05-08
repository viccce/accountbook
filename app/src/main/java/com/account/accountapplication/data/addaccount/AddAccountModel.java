package com.account.accountapplication.data.addaccount;

import com.account.accountapplication.data.record.Account;

public interface AddAccountModel {

    void createAccount(Account account, FinishListener finishListener);

    interface FinishListener{
        void createAccountSuccess();

        void onError(String message);
    }
}
