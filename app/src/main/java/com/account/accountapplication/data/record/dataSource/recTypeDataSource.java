package com.account.accountapplication.data.record.dataSource;

import androidx.annotation.NonNull;

import com.account.accountapplication.data.record.recType;


public interface recTypeDataSource {

    recType getRecType(@NonNull Integer recTypeId);
}
