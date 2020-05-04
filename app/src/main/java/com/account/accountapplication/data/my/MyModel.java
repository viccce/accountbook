package com.account.accountapplication.data.my;

public interface MyModel {

    void getUserInfo(FinishListener finishListener);

    public interface FinishListener{
        void getUserInfoFinished(User user);
    }
}
