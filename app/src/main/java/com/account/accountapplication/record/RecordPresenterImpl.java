package com.account.accountapplication.record;

import com.account.accountapplication.data.record.Account;
import com.account.accountapplication.data.record.AccountModel;
import com.account.accountapplication.data.record.AccountModelImpl;
import com.account.accountapplication.utils.DataManager;

import java.util.List;

public class RecordPresenterImpl implements RecordContract.RecordPresenter, AccountModel.FinishListener {

    private RecordContract.RecordView recordView;
    private AccountModel accountModel;
    private DataManager dataManager;

    public RecordPresenterImpl(RecordContract.RecordView recordView) {
        this.recordView = recordView;
        accountModel = new AccountModelImpl();
        dataManager = DataManager.getInstance();
    }

    @Override
    public void getAccountInfoList() {
        Long userId = dataManager.getUserId();
        if(userId == null){
            this.onError("无法获取当前登录人");
            return;
        }
        accountModel.getAccountInfoList(userId, this);
    }

    @Override
    public void deleteAccount(Account account) {
        accountModel.deleteAccount(account, this);
    }

    @Override
    public void getAccountInfoListSuccess(List<Account> list) {
        dataManager.setAccountList(list);
        recordView.showAccountInfoList(list);
    }

    @Override
    public void deleteAccountSuccess() {
        this.getAccountInfoList();
    }

    @Override
    public void onError(String message) {
        recordView.onError(message);
    }
}
