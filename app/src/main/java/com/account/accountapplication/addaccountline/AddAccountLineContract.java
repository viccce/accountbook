package com.account.accountapplication.addaccountline;

public interface AddAccountLineContract {

    interface AddAccountLineView {

        void showError(String message);

        void saveSuccess();
    }

    interface AddAccountLinePresenter{
        void saveAccountLine(long accountId, String accountType, String changeMoney, String payType);

        String[] getAccountNameList();

        Long getAccountIdByName(String name);
    }
}
