package com.account.accountapplication.login;

import com.account.accountapplication.data.login.LoginModel;
import com.account.accountapplication.data.login.LoginModelImpl;
import com.account.accountapplication.data.my.User;
import com.account.accountapplication.utils.DataManager;


/**
 * 1 完成presenter的实现。这里面主要是Model层和View层的交互和操作。
 * 2  presenter里面还有个OnLoginFinishedListener，
 * 其在Presenter层实现，给Model层回调，更改View层的状态，
 * 确保 Model层不直接操作View层。如果没有这一接口在LoginPresenterImpl实现的话，
 */
public class LoginPresenterImpl implements LoginContract.LoginPresenter, LoginModel.OnLoginFinishedListener {
    private LoginContract.LoginView loginView;
    private LoginModel loginModel;
    private DataManager dataManager;

    public LoginPresenterImpl(LoginContract.LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
        this.dataManager = DataManager.getInstance();
    }

    @Override
    public void validateCredentials(String username, String password) {
        int flag;
        if (loginView != null) {
            loginView.showProgress();
        }
        loginModel.login(username, password, this);

    }

    @Override
    public void createInfo(User user) {
        if (loginView != null) {
            loginView.showProgress();
        }
        loginModel.createInfo(user, this);
    }


    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onRemoteSuccess(User result) {
        //缓存userId
        dataManager.setUserId(result.getId());
        //获得结果插入本地数据库中
        this.createInfo(result);
    }

    @Override
    public void onCreateInfoSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }
}
