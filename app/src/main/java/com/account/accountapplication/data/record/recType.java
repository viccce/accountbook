package com.account.accountapplication.data.record;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "recType")
public class recType {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="recType_id")
    private   Integer recTypeId;

    @ColumnInfo(name="recType_name")
    @NonNull
    private   String recTypeName;

    public recType(@NonNull Integer recTypeId, @NonNull String recTypeName) {
        this.recTypeId = recTypeId;
        this.recTypeName = recTypeName;
    }

    @NonNull
    public Integer getRecTypeId() {
        return recTypeId;
    }

    @NonNull
    public String getRecTypeName() {
        return recTypeName;
    }

    public void setRecTypeId(@NonNull Integer recTypeId) {
        this.recTypeId = recTypeId;
    }

    public void setRecTypeName(@NonNull String recTypeName) {
        this.recTypeName = recTypeName;
    }
}
