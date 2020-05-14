package com.account.accountapplication.accountuser;

import com.account.accountapplication.data.my.User;

import java.util.List;

public interface AccountUserContract {

    interface AccountUserView {

        void showErrorMessage(String message);

        void showAccountUserList(List<User> list);

        void getUserListSuccess(List<User> list);

    }

    interface AccountUserPresenter {

        void deleteAccountUser(User user);

        void addAccountUser(User user);

        void getAccountUserList(long accountId);

        void getUserList();
    }
}
