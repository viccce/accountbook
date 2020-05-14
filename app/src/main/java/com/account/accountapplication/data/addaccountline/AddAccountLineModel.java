package com.account.accountapplication.data.addaccountline;

import com.account.accountapplication.data.record.AccountLine;

public interface AddAccountLineModel {

    void saveAccountLine(AccountLine line, FinishListener finishListener);

    interface FinishListener{
        void onError(String message);

        void saveSuccess();
    }
}
