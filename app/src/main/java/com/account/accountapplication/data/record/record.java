package com.account.accountapplication.data.record;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "record")
public final class record {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="record_id")
    private   Integer recordId;

    @ColumnInfo(name="record_server")
    @NonNull
    private   Integer recordServer;

    @ColumnInfo(name="record_name")
    @NonNull
    private   String recordName;

    @ColumnInfo(name="record_inOut")
    @NonNull
    private   Integer recordInOut;

    @ColumnInfo(name="record_money")
    @NonNull
    private   float recordMoney;



    @ColumnInfo(name="record_date")
    @NonNull
    private  Date recordDate;

    @ColumnInfo(name="record_createDate")
    @NonNull
    private   Date recordCreateDate;

    @ColumnInfo(name="record_modifyDate")
    private   Date recordModifyDate;

    @ColumnInfo(name="recordAccount_id")
    @ForeignKey(entity = account.class, parentColumns = "account_id", childColumns = "recordAccount_id")
    private   Integer account;

    @ColumnInfo(name="recordRecType_id")
    @ForeignKey(entity = recType.class, parentColumns = "recType_id", childColumns = "recordRecType_id")
    private   Integer  recType;




    @NonNull
    public Integer getRecordId() {
        return recordId;
    }

    @NonNull
    public Integer getRecordServer() {
        return recordServer;
    }

    @NonNull
    public String getRecordName() {
        return recordName;
    }

    @NonNull
    public Integer getRecordInOut() {
        return recordInOut;
    }

    @NonNull
    public float getRecordMoney() {
        return recordMoney;
    }



    @NonNull
    public Date getRecordDate() {
        return recordDate;
    }

    @NonNull
    public Date getRecordCreateDate() {
        return recordCreateDate;
    }

    public Date getRecordModifyDate() {
        return recordModifyDate;
    }



    public void setRecordId(@NonNull Integer recordId) {
        this.recordId = recordId;
    }

    public void setRecordServer(@NonNull Integer recordServer) {
        this.recordServer = recordServer;
    }

    public void setRecordName(@NonNull String recordName) {
        this.recordName = recordName;
    }

    public void setRecordInOut(@NonNull Integer recordInOut) {
        this.recordInOut = recordInOut;
    }

    public void setRecordMoney(float recordMoney) {
        this.recordMoney = recordMoney;
    }

    public void setRecordDate(@NonNull Date recordDate) {
        this.recordDate = recordDate;
    }

    public void setRecordCreateDate(@NonNull Date recordCreateDate) {
        this.recordCreateDate = recordCreateDate;
    }

    public void setRecordModifyDate(Date recordModifyDate) {
        this.recordModifyDate = recordModifyDate;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Integer getRecType() {
        return recType;
    }

    public void setRecType(Integer recType) {
        this.recType = recType;
    }

    public record(@NonNull Integer recordId, @NonNull Integer recordServer, @NonNull String recordName, @NonNull Integer recordInOut, float recordMoney, @NonNull Date recordDate, @NonNull Date recordCreateDate, Date recordModifyDate, Integer account, Integer recType) {
        this.recordId = recordId;
        this.recordServer = recordServer;
        this.recordName = recordName;
        this.recordInOut = recordInOut;
        this.recordMoney = recordMoney;
        this.recordDate = recordDate;
        this.recordCreateDate = recordCreateDate;
        this.recordModifyDate = recordModifyDate;
        this.account = account;
        this.recType = recType;
    }
}
