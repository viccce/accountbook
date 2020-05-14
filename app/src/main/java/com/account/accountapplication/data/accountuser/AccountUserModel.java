package com.account.accountapplication.data.accountuser;

import com.account.accountapplication.data.my.User;

import java.util.List;

public interface AccountUserModel {

    void deleteAccountUser(User user, FinishListener listener);

    void addAccountUser(User user, FinishListener listener);

    void getAccountUserList(long accountId, FinishListener listener);

    void getUserList(FinishListener listener);

    interface FinishListener{

        void onError(String message);

        void modifyAccountUserSuccess(User user);

        void getAccountUserListSuccess(List<User> user);

        void getUserListSuccess(List<User> list);
    }
}
