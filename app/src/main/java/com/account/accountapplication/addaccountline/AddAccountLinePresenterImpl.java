package com.account.accountapplication.addaccountline;

import com.account.accountapplication.data.addaccountline.AddAccountLineModel;
import com.account.accountapplication.data.addaccountline.AddAccountLineModelImpl;
import com.account.accountapplication.data.record.Account;
import com.account.accountapplication.data.record.AccountLine;
import com.account.accountapplication.utils.DataManager;

import java.math.BigDecimal;
import java.util.List;

public class AddAccountLinePresenterImpl implements AddAccountLineContract.AddAccountLinePresenter, AddAccountLineModel.FinishListener {

    private AddAccountLineContract.AddAccountLineView addAccountLineView;
    private AddAccountLineModel addAccountLineModel;
    private DataManager dataManager;

    AddAccountLinePresenterImpl(AddAccountLineContract.AddAccountLineView addAccountLineView) {
        this.addAccountLineView = addAccountLineView;
        this.addAccountLineModel = new AddAccountLineModelImpl();
        dataManager = DataManager.getInstance();
    }

    @Override
    public void saveAccountLine(long accountId, String accountType, String changeMoney, String payType) {
        AccountLine line = new AccountLine();
        line.setAccountId(accountId);
        line.setAccountType(accountType);
        line.setPayType(payType);
        line.setChangeUserId(dataManager.getUserId());
        BigDecimal money = new BigDecimal(changeMoney);
        money = money.multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP);
        line.setChangeMoney(money.longValue());
        addAccountLineModel.saveAccountLine(line, this);
    }

    @Override
    public String[] getAccountNameList() {
        List<Account> accountList = dataManager.getAccountList();
        String[] array = new String[accountList.size()];
        for (int i = 0; i < accountList.size(); i++) {
            array[i] = accountList.get(i).getAccountName();
        }
        return array;
    }

    @Override
    public Long getAccountIdByName(String name) {
        List<Account> accountList = dataManager.getAccountList();
        for (Account account : accountList) {
            if (name.equals(account.getAccountName())) {
                return account.getAccountId();
            }
        }
        return null;
    }

    @Override
    public void onError(String message) {
        addAccountLineView.showError(message);
    }

    @Override
    public void saveSuccess() {
        addAccountLineView.saveSuccess();
    }
}
