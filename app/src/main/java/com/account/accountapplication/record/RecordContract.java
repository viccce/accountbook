package com.account.accountapplication.record;

import com.account.accountapplication.data.record.Account;

import java.util.List;

public interface RecordContract {

    public interface RecordView {

        void showAccountInfoList(List<Account> list);

        void onError(String message);
    }

    public interface RecordPresenter {

        void getAccountInfoList();

        void deleteAccount(Account account);
    }
}
