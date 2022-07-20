package com.zen.dao;

import com.zen.entity.User;

import java.util.List;

public interface UserDao {
    User findUserById();
    List<User> findAllUser();
    List<User>findUserByUserName(String username);
    void save(User user);
}

