package com.account.accountapplication.edituser;

import com.account.accountapplication.data.my.User;

public interface EditUserContract {

    interface EditUserView{

        void textEmpty(String message);

        void setUserInfo(User user);

        void updateSuccess(User user);
    }

    interface EditUserPresenter{

        void saveRemoteUserInfo(Long id, String nickname, String mobile, String email, String remark);

        void getUserInfo();
    }
}
