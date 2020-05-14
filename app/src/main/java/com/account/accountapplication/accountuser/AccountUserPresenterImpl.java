package com.account.accountapplication.accountuser;

import com.account.accountapplication.data.accountuser.AccountUserModel;
import com.account.accountapplication.data.accountuser.AccountUserModelImpl;
import com.account.accountapplication.data.my.User;
import com.account.accountapplication.utils.DataManager;

import java.util.List;

public class AccountUserPresenterImpl implements AccountUserContract.AccountUserPresenter, AccountUserModel.FinishListener {

    AccountUserContract.AccountUserView accountUserView;
    AccountUserModel accountUserModel;

    AccountUserPresenterImpl(AccountUserContract.AccountUserView accountUserView) {
        this.accountUserView = accountUserView;
        accountUserModel = new AccountUserModelImpl();
    }

    @Override
    public void deleteAccountUser(User user) {
        accountUserModel.deleteAccountUser(user, this);
    }

    @Override
    public void addAccountUser(User user) {
        accountUserModel.addAccountUser(user, this);
    }

    @Override
    public void getAccountUserList(long accountId) {
        accountUserModel.getAccountUserList(accountId, this);
    }

    @Override
    public void getUserList() {
        accountUserModel.getUserList(this);
    }

    @Override
    public void onError(String message) {
        accountUserView.showErrorMessage(message);
    }

    @Override
    public void modifyAccountUserSuccess(User user) {
        this.getAccountUserList(user.getAccountId());
    }

    @Override
    public void getAccountUserListSuccess(List<User> user) {
        accountUserView.showAccountUserList(user);
    }

    @Override
    public void getUserListSuccess(List<User> list) {
        accountUserView.getUserListSuccess(list);
    }
}
