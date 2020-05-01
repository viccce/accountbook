

package com.account.accountapplication.data.record.dataBase;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.account.accountapplication.data.record.dao.accountDao;
import com.account.accountapplication.data.record.dao.recTypeDao;
import com.account.accountapplication.data.record.recType;
import com.account.accountapplication.data.record.record;


/**
 * The Room Database that contains the Task table.
 */
@Database(entities = {recType.class, record.class}, version = 1,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class recTypeDatabase extends RoomDatabase {

    private static recTypeDatabase INSTANCE;

public recTypeDao recTypeDao;
    public abstract recTypeDao recTypeDao();

    public recTypeDao getRecTypeDao(){
        return recTypeDao;
    }
    private static final Object sLock = new Object();

    public static recTypeDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        recTypeDatabase.class, "recType.db")
                        .build();
            }
            return INSTANCE;
        }
    }

}
