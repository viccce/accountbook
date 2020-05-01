

package com.account.accountapplication.data.record.dataBase;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.account.accountapplication.data.record.account;
import com.account.accountapplication.data.record.dao.recordDao;
import com.account.accountapplication.data.record.recType;
import com.account.accountapplication.data.record.record;

import java.io.File;

import static androidx.constraintlayout.solver.SolverVariable.Type.CONSTANT;


/**
 * The Room Database that contains the Task table.
 */
@Database(entities = {record.class, account.class, recType.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class recordDatabase extends RoomDatabase {

    private static recordDatabase INSTANCE;

    public  recordDao recordDao;

    private static final Object sLock = new Object();

    public  recordDao getRecordDao(){
        return recordDao;
    }

    public abstract recordDao recordDao() ;
    public static recordDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {

                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        recordDatabase.class, "record.db")
                        .addCallback(new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Log.w("WangJ", "onCreate:" + db.getPath());
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
                Log.w("WangJ", "onOpen:" + db.getPath());
            }
        }) .build();
            }
            return INSTANCE;
        }
    }




}
