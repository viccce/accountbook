package com.account.accountapplication.account;

import com.account.accountapplication.data.record.AccountLine;

import java.util.List;

public interface AccountContract {
    interface AccountView{
        void getAccountLineSuccess(List<AccountLine> list);

        void onError(String message);
    }
    
    interface AccountPresenter{

        void getAccountLine(Long accountId, String accountType);
    }
}
