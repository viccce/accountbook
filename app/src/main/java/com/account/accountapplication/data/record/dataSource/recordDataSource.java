package com.account.accountapplication.data.record.dataSource;

import androidx.annotation.NonNull;


import com.account.accountapplication.data.record.record;

import java.util.List;

public interface recordDataSource {

    interface LoadRecordCallback {

        void onRecordLoaded(List<record> records);

        void onDataNotAvailable();
    }

    interface GetRecordCallback {

        void onRecordLoaded(record record);

        void onDataNotAvailable();
    }

    void getRecord(@NonNull LoadRecordCallback callback);

    List<record> getRecord();

    void refreshRecords();

    record getRecord(@NonNull Integer recordId);

    void getRecord(@NonNull Integer recordId, @NonNull GetRecordCallback callback);

    void saveRecord(@NonNull record record);
}
