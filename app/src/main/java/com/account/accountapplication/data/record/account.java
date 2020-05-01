package com.account.accountapplication.data.record;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "account")
public class account {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="account_id")
    private   Integer accountId;

    @NonNull
    @ColumnInfo(name="account_user")
    private   Integer userId;

    @NonNull
    public Integer getAccountId() {
        return accountId;
    }

    public account(@NonNull Integer accountId, @NonNull Integer userId) {
        this.accountId = accountId;
        this.userId = userId;
    }

    @NonNull
    public Integer getUserId() {
        return userId;
    }

    public void setAccountId(@NonNull Integer accountId) {
        this.accountId = accountId;
    }

    public void setUserId(@NonNull Integer userId) {
        this.userId = userId;
    }
}
