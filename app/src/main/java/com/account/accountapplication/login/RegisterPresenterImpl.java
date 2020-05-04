package com.account.accountapplication.login;

import com.account.accountapplication.data.login.LoginModel;
import com.account.accountapplication.data.login.LoginModelImpl;
import com.account.accountapplication.data.login.RegisterModel;
import com.account.accountapplication.data.login.RegisterModelImpl;

/**
 * 完成presenter的实现。这里面主要是Model层和View层的交互和操作。
 * 其在Presenter层实现，给Model层回调，更改View层的状态，
 * 确保 Model层不直接操作View层。如果没有这一接口在LoginPresenterImpl实现的话，
 */
public class RegisterPresenterImpl implements RegisterContract.RegisterPresenter,RegisterModel.OnRegisterFinishedListener {

    private RegisterContract.RegisterView registerView;
    private RegisterModel registerModel;

    public RegisterPresenterImpl(RegisterContract.RegisterView registerView) {
        this.registerView = registerView;
        this.registerModel = new RegisterModelImpl();
    }

    @Override
    public void validateCredentials(String username, String password, String email, String mobile) {
        if (registerView != null) {
            registerView.showProgress();
        }
        registerModel.register(username, password,email, mobile, this);
    }

    @Override
    public void onDestroy() {
        registerView=null;
    }

    @Override
    public void onUsernameError() {

    }

    @Override
    public void onPasswordError() {

    }

    @Override
    public void onEmailError() {

    }

    @Override
    public void onSuccess() {
        if (registerView != null) {
            registerView.navigateToHome();
        }
    }
}
