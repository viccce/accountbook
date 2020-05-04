package com.account.accountapplication.my;

import com.account.accountapplication.data.my.User;

public interface MyContract {

    public interface MyView{

        void displayUserInfo(User user);
    }

    public interface MyPresenter{
        void getUserInfo();
    }
}
