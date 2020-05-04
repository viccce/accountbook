package com.account.accountapplication.data.login;

public interface RegisterModel {

    void register(String username, String password, String email, String mobile, OnRegisterFinishedListener listener);

    public interface OnRegisterFinishedListener {

        void onUsernameError();

        void onPasswordError();

        void onEmailError();

        void onSuccess();
    }
}
