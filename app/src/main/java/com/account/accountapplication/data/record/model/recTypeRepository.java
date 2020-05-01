package com.account.accountapplication.data.record.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;

import com.account.accountapplication.data.record.account;
import com.account.accountapplication.data.record.dao.accountDao;
import com.account.accountapplication.data.record.dao.recTypeDao;
import com.account.accountapplication.data.record.dataBase.accountDatabase;
import com.account.accountapplication.data.record.dataBase.recTypeDatabase;
import com.account.accountapplication.data.record.recType;

import java.util.List;

public class recTypeRepository {

    private LiveData<List<recType>> allRecTypeLive;
    private recTypeDao rDao;

    public recTypeRepository(Context context) {
        recTypeDatabase rdatabase= recTypeDatabase.getInstance(context.getApplicationContext());
        rDao = rdatabase.recTypeDao();
        allRecTypeLive = rDao.getAllRecTypesLive();
    }

    //异步添加（不在主线程）
    public void insertRecTypes(recType... recTypes) {
        new recTypeRepository.InsertAsyncTask(rDao).execute(recTypes);
    }

    //添加
    static class InsertAsyncTask extends AsyncTask<recType, Void, Void> {
        private recTypeDao recDao;

        InsertAsyncTask(recTypeDao recTypeDao) {
            this.recDao = recTypeDao;
        }

        @Override
        protected Void doInBackground(recType... recTypes) {
            recDao.insertRecType(recTypes);
            return null;
        }
    }
}
