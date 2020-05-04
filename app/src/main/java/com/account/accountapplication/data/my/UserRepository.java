package com.account.accountapplication.data.my;

import com.account.accountapplication.data.LiveDataBase;
import com.account.accountapplication.data.my.datasource.UserDataSource;
import com.account.accountapplication.data.my.datasource.UserDataSourceImpl;

import java.util.List;

public class UserRepository implements UserDataSource {

    private static UserRepository INSTANCE;

    private final UserDataSourceImpl userRepository;

    private UserRepository() {
        this.userRepository = UserDataSourceImpl.getInstance(LiveDataBase.getInstance().userDao());
    }

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }
        return INSTANCE;
    }

    @Override
    public List<User> getUser() {
        return userRepository.getUser();
    }

    @Override
    public void create(User user) {
        userRepository.create(user);
    }

    @Override
    public void delete() {
        userRepository.delete();
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
}
