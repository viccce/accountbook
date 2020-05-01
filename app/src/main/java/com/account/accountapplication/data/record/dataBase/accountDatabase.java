
package com.account.accountapplication.data.record.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.account.accountapplication.data.record.account;
import com.account.accountapplication.data.record.dao.accountDao;
import com.account.accountapplication.data.record.dao.recordDao;
import com.account.accountapplication.data.record.record;


/**
 * The Room Database that contains the Task table.
 */
@Database(entities = {account.class, record.class}, version = 1,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class accountDatabase extends RoomDatabase {

    private static accountDatabase INSTANCE;
    public  accountDao accountDao;


    public abstract accountDao accountDao();
    public accountDao getAccountDao(){
        return accountDao;
    }

    private static final Object sLock = new Object();

    public static accountDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        accountDatabase.class, "account.db")
                        .build();
            }
            return INSTANCE;
        }
    }

}
