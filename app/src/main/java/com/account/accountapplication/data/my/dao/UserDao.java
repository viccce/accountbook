package com.account.accountapplication.data.my.dao;

import com.account.accountapplication.data.my.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("DELETE FROM USER")
    void deleteUser();

    @Query("SELECT * FROM USER")
    List<User> getUser();

    @Update
    void updateUser(User user);
}
