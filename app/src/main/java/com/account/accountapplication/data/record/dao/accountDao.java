package com.account.accountapplication.data.record.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.account.accountapplication.data.record.account;
import com.account.accountapplication.data.record.record;

import java.util.List;

@Dao
public interface accountDao {

    @Query("SELECT * FROM account")
    List<account> getAccount();

    @Query("SELECT * FROM account WHERE account_id = :accountId")
    account getAccountById(Integer accountId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAccount(account... accounts);

    //获取所有record
    @Query("SELECT * FROM account ORDER BY account_id DESC ")
    LiveData<List<account>> getAllAccountsLive();
}
