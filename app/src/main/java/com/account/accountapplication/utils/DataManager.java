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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
