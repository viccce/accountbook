package com.account.accountapplication.data.record.dao;

import androidx.room.ColumnInfo;

public class InnerJoinResult {

    @ColumnInfo(name="recType_name")
    private String recType_name;

    public String getRecType_name() {
        return recType_name;
    }

    public void setRecType_name(String recType_name) {
        this.recType_name = recType_name;
    }
}
