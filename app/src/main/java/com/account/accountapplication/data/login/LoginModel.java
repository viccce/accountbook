package com.account.accountapplication.data.login;

import com.account.accountapplication.data.my.User;

public interface LoginModel {
    void login(String username, String password, OnLoginFinishedListener listener);

    void createInfo(User user, OnLoginFinishedListener listener);

    public interface OnLoginFinishedListener {

        void onUsernameError();

        void onPasswordError();

        void onRemoteSuccess(User result);

        void onCreateInfoSuccess();
    }
}
