package com.account.accountapplication.data.record.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.account.accountapplication.data.record.record;

import java.util.List;

@Dao
public interface recordDao {


    //获取所有record
    @Query("SELECT * FROM record ORDER BY record_id DESC ")
    LiveData<List<record>> getAllRecordsLive();

    @Query("SELECT * FROM record WHERE record_id = :recordId")
    record getRecordById(Integer recordId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecord(record... records);

    @Update
    int updateRecord(record... records);

//    @Query("UPDATE record SET completed = :completed WHERE entryid = :taskId")
//    void updateCompleted(String taskId, boolean completed);

    @Query("DELETE FROM record WHERE record_id = :recordId")
    int deleteRecordById(Integer recordId);

    @Query("DELETE FROM record")
    void deleteAllRecords();

    //删除
    @Delete
    void deleteRecords(record... records);

    //根据关键词查询单词
    @Query("SELECT * FROM record WHERE record_name LIKE :pattern ORDER BY record_id DESC")
    LiveData<List<record>> findRecordsWithPattern(String pattern);

    //使用内连接查询
    @Query("SELECT recType_name from record INNER JOIN recType ON recType.recType_id=record.recordRecType_id")
    LiveData<List<InnerJoinResult>> getRecFromRecord();
}
