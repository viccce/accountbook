package com.account.accountapplication.login;

public interface RegisterContract {

    public interface RegisterView{
        void showProgress();

        void hideProgress();

        void setUsernameError();

        void setPasswordError();

        void setEmailError();

        void navigateToHome();
    }

    public interface  RegisterPresenter{
        void validateCredentials(String username, String password,String email, String mobile);

        void onDestroy();
    }
}
