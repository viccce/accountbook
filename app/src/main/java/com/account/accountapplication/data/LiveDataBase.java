package com.account.accountapplication.data;

import com.account.accountapplication.MyApp;
import com.account.accountapplication.data.my.User;
import com.account.accountapplication.data.my.dao.UserDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class LiveDataBase extends RoomDatabase {

    public static LiveDataBase INSTANCE;

    public abstract UserDao userDao();

    private static final Object sLock = new Object();

    public static LiveDataBase getInstance() {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(MyApp.getInstance(),
                        LiveDataBase.class, "bishe.db")
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return INSTANCE;
        }
    }
}
