package com.account.accountapplication.my;

import com.account.accountapplication.data.my.MyModel;
import com.account.accountapplication.data.my.MyModelImpl;
import com.account.accountapplication.data.my.User;

public class MyPresenterImpl implements MyContract.MyPresenter, MyModel.FinishListener {

    private MyContract.MyView myView;
    private MyModel myModel;

    public MyPresenterImpl(MyContract.MyView myView) {
        this.myView = myView;
        myModel = new MyModelImpl();
    }

    @Override
    public void getUserInfo() {
        myModel.getUserInfo(this);
    }

    @Override
    public void getUserInfoFinished(User user) {
        myView.displayUserInfo(user);
    }
}
