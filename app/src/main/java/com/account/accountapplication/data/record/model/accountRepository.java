package com.account.accountapplication.data.record.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.account.accountapplication.data.record.account;
import com.account.accountapplication.data.record.dao.accountDao;
import com.account.accountapplication.data.record.dao.recordDao;
import com.account.accountapplication.data.record.dataBase.accountDatabase;
import com.account.accountapplication.data.record.dataBase.recordDatabase;
import com.account.accountapplication.data.record.record;

import java.util.List;

public class accountRepository {

    private LiveData<List<account>> allAccountsLive;
    private accountDao aDao;

    public accountRepository(Context context) {
        accountDatabase adatabase= accountDatabase.getInstance(context.getApplicationContext());
        aDao = adatabase.accountDao();
        allAccountsLive = aDao.getAllAccountsLive();
    }

    //异步添加（不在主线程）
    public void insertAccounts(account... accounts) {
        new InsertAsyncTask(aDao).execute(accounts);
    }

    //添加
    static class InsertAsyncTask extends AsyncTask<account, Void, Void> {
        private accountDao accDao;

        InsertAsyncTask(accountDao accountDao) {
            this.accDao = accountDao;
        }

        @Override
        protected Void doInBackground(account... accounts) {
            accDao.insertAccount(accounts);
            return null;
        }
    }

}
