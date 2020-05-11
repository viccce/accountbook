package com.account.accountapplication.data.account;

import com.account.accountapplication.data.record.Account;
import com.account.accountapplication.data.record.AccountLine;

import java.util.List;

public interface AccountModel {

    void getAccountLine(Account account, FinishListener listener);

    interface FinishListener{
        void getAccountLineFinished(List<AccountLine> list);

        void onError(String message);
    }
}
