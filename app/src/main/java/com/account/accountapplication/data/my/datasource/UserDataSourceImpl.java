package com.account.accountapplication.data.my.datasource;

import com.account.accountapplication.data.my.User;
import com.account.accountapplication.data.my.dao.UserDao;

import java.util.List;

public class UserDataSourceImpl implements UserDataSource {

    private static UserDataSourceImpl INSTANCE;

    private UserDao userDao;

    public UserDataSourceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public static UserDataSourceImpl getInstance(UserDao userDao) {
        if (INSTANCE == null) {
            INSTANCE = new UserDataSourceImpl(userDao);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public  List<User> getUser() {
        return userDao.getUser();
    }

    @Override
    public void create(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void delete() {
        userDao.deleteUser();
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
