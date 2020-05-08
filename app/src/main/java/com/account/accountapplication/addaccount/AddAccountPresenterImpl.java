package com.account.accountapplication.addaccount;

import com.account.accountapplication.data.addaccount.AddAccountModel;
import com.account.accountapplication.data.addaccount.AddAccountModelImpl;
import com.account.accountapplication.data.record.Account;
import com.account.accountapplication.utils.DataManager;

public class AddAccountPresenterImpl implements AddAccountContract.AddAccountPresenter, AddAccountModel.FinishListener {

    private AddAccountContract.AddAccountView addAccountView;
    private AddAccountModel addAccountModel;
    private DataManager dataManager;

    public AddAccountPresenterImpl(AddAccountContract.AddAccountView addAccountView) {
        this.addAccountModel = new AddAccountModelImpl();
        this.addAccountView = addAccountView;
        this.dataManager = DataManager.getInstance();
    }

    @Override
    public void createAccount(String accountName) {
        if(dataManager != null && dataManager.getUserId() != null){
            Account account = new Account();
            account.setAccountName(accountName);
            account.setActionType("create");
            account.setUserId(dataManager.getUserId());
            addAccountModel.createAccount(account, this);
        }else{
            onError("获取不到登录信息,请重新登录");
        }
    }

    @Override
    public void createAccountSuccess() {
        addAccountView.createSuccess();
    }

    @Override
    public void onError(String message) {
        addAccountView.showErrorMessage(message);
    }
}
