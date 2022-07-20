package com.zen.dao;

import com.zen.annoation.Bean;
import com.zen.entity.User;

import java.util.List;
@Bean
public class UserDaoImpl implements UserDao {
    @Override
    public User findUserById() {
        System.out.println("这里是UserDao-findUserById");
        return null;
    }

    @Override
    public List<User> findAllUser() {
        System.out.println("这里是UserDao-findAllUser");
        return null;
    }

    @Override
    public List<User> findUserByUserName(String findUserByUserName) {
        System.out.println("这里是UserDao-findUserById");
        return null;
    }

    @Override
    public void save(User user) {
        System.out.println("这里是UserDao-save");
    }
}


