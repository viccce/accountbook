package com.account.accountapplication.data.record.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.account.accountapplication.data.record.dao.InnerJoinResult;
import com.account.accountapplication.data.record.record;

import java.util.List;

public class RecordModel extends AndroidViewModel {

    private recordRepository repository;

    public RecordModel(@NonNull Application application) {
        super(application);
        repository = new recordRepository(application);
    }

    //获取所有单词
    public LiveData<List<record>> getAllRecordsLive() {
        return repository.getAllRecordsLive();
    }
    //获取所有单词
    public LiveData<List<InnerJoinResult>> findRecType() {
        return repository.findRecType();
    }

    //根据查询条件获得单词
    LiveData<List<record>> findRecordsWithPattern(String pattern) {
        return repository.findRecordsWithPattern(pattern);
    }

    //增加
    void insertRecords(record... records) {
        repository.insertRecords(records);
    }

    //更新
    void updateRecords(record... records) {
        repository.updateRecords(records);
    }

    //删除
    void deleteRecords(record... records) {
        repository.deleteRecords(records);
    }

    //清空
    void deleteAllRecords() {
        repository.deleteAllRecords();
    }
}
