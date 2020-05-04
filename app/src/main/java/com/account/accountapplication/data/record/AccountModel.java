package com.account.accountapplication.data.record;

import java.util.List;

public interface AccountModel {

    void getAccountInfoList(Long userId, FinishListener listener);

    interface FinishListener {

        void getAccountInfoListSuccess(List<Account> list);

        void onError(String message);
    }
}
