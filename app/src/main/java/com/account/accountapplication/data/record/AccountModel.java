package com.account.accountapplication.data.record;

import java.util.List;

public interface AccountModel {

    void getAccountInfoList(Long userId, FinishListener listener);

    void deleteAccount(Account account, FinishListener listener);

    interface FinishListener {

        void getAccountInfoListSuccess(List<Account> list);

        void deleteAccountSuccess();

        void onError(String message);
    }
}
