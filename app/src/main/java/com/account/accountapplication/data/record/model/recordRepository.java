package com.account.accountapplication.data.record.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.account.accountapplication.data.record.dao.InnerJoinResult;
import com.account.accountapplication.data.record.dao.recordDao;
import com.account.accountapplication.data.record.record;

import java.util.List;

public class recordRepository {

    private LiveData<List<record>> allRecordsLive;
    private recordDao recordDao;

    public recordRepository(Context context) {
        recordDatabase Database = recordDatabase.getInstance(context.getApplicationContext());
        recordDao = Database.recordDao();
        allRecordsLive = recordDao.getAllRecordsLive();
    }

    //根据条件查询
    LiveData<List<InnerJoinResult>> findRecType() {
        return recordDao.getRecFromRecord();
    }

    //根据条件查询
    LiveData<List<record>> findRecordsWithPattern(String pattern) {
        return recordDao.findRecordsWithPattern("%" + pattern + "%");
    }

    //异步添加（不在主线程）
    public void insertRecords(record... records) {
        new InsertAsyncTask(recordDao).execute(records);
    }

    //异步更新（不在主线程）
    void updateRecords(record... records) {
        new UpdateAsyncTask(recordDao).execute(records);
    }

    //异步删除（不在主线程）
    void deleteRecords(record... records) {
        new DeleteAsyncTask(recordDao).execute(records);
    }

    //异步删除所有（不在主线程）
    void deleteAllRecords() {
        new DeleteAllAsyncTask(recordDao).execute();
    }

    //获取所有的单词
    public LiveData<List<record>> getAllRecordsLive() {
        return allRecordsLive;
    }


    //添加
    static class InsertAsyncTask extends AsyncTask<record, Void, Void> {
        private recordDao recordDao;

        InsertAsyncTask(recordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(record... records) {
            recordDao.insertRecord(records);
            return null;
        }
    }

    //更新
    static class UpdateAsyncTask extends AsyncTask<record, Void, Void> {
        private recordDao recordDao;

        UpdateAsyncTask(recordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(record... records) {
            recordDao.updateRecord(records);
            return null;
        }
    }

    //删除
    static class DeleteAsyncTask extends AsyncTask<record, Void, Void> {
        private recordDao recordDao;

        DeleteAsyncTask(recordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(record... records) {
            recordDao.deleteRecords(records);
            return null;
        }
    }

    //删除所有
    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private recordDao recordDao;

        DeleteAllAsyncTask(recordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recordDao.deleteAllRecords();
            return null;
        }
    }



}
