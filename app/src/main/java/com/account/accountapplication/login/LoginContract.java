package com.account.accountapplication.login;

public interface LoginContract {

    public interface LoginView{
        void showProgress();

        void hideProgress();

        void setUsernameError();

        void setPasswordError();

        void navigateToHome();
    }

    public interface  LoginPresenter{
        void validateCredentials(String username, String password);

        void onDestroy();


    }
}
