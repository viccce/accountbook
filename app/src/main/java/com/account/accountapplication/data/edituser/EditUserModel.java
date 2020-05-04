package com.account.accountapplication.data.edituser;

import com.account.accountapplication.data.my.User;

public interface EditUserModel {

    void saveRemoteUserInfo(User user, FinishListener listener);

    void getUserInfo(FinishListener listener);

    void updateUserInfo(User user, FinishListener listener);

    public interface FinishListener{

        void onError(String message);

        void remoteSaveSuccess(User user);

        void getUserInfoSuccess(User user);

        void updateUserInfoSuccess(User user);
    }
}
