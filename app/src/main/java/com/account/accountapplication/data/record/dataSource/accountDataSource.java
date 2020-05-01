package com.account.accountapplication.data.record.dataSource;

import androidx.annotation.NonNull;

import com.account.accountapplication.data.record.account;


public interface accountDataSource {

    account getAccount(@NonNull Integer accountId);
}
