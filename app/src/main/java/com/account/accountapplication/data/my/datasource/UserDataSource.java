package com.account.accountapplication.data.my.datasource;

import com.account.accountapplication.data.my.User;

import java.util.List;

public interface UserDataSource {

    List<User> getUser();

    void create(User user);

    void delete();

    void updateUser(User user);
}
