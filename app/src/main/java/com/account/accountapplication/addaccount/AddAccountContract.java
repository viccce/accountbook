package com.account.accountapplication.addaccount;

public interface AddAccountContract {

    interface AddAccountView{

        void showErrorMessage(String message);

        void createSuccess();
    }

    interface AddAccountPresenter{

        void createAccount(String accountName);
    }
}
