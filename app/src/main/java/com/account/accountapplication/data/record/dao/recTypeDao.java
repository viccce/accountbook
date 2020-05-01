package com.account.accountapplication.data.record.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.account.accountapplication.data.record.account;
import com.account.accountapplication.data.record.recType;

import java.util.List;

@Dao
public interface recTypeDao {

    @Query("SELECT * FROM recType")
    List<recType> getRecType();

    @Query("SELECT * FROM recType WHERE recType_id = :recTypeId")
    recType getRecTypeById(Integer recTypeId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecType(recType... recTypes);

    //获取所有record
    @Query("SELECT * FROM recType ORDER BY recType_id DESC ")
    LiveData<List<recType>> getAllRecTypesLive();
}