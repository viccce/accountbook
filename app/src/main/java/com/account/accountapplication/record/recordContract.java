package com.account.accountapplication.record;

public interface recordContract {

    public interface recordView {
        void shoeRecord();
        void addRecord();
    }

    public interface recordPresenter{
        void getRecord();
        void addRecord();
    }
}
