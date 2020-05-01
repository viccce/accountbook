package com.account.accountapplication.data.login;

public interface LoginModel {
    void login(String username, String password, OnLoginFinishedListener listener);

    public interface OnLoginFinishedListener {

        void onUsernameError();

        void onPasswordError();

        void onSuccess();
    }
}
