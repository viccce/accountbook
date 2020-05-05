package com.account.accountapplication.utils;

public class DataManager {

    private static volatile DataManager dataManager;

    public static DataManager getInstance(){
        if(dataManager == null){
            synchronized (DataManager.class){
                if(dataManager == null){
                    dataManager = new DataManager();
                }
            }
        }
        return dataManager;
    }

    private Long userId;

    private Long accountId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
