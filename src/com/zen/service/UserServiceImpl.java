package com.zen.service;

import com.zen.dao.UserDao;
import com.zen.dao.UserDaoImpl;
import com.zen.entity.User;

public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();
    @Override
    public void login() {
        userDao.findUserByUserName("刘水龙");
        System.out.println("登录业务的实现");
    }

    @Override
    public void regist() {
        userDao.save(new User(1,"刘水龙","222"));
        System.out.println("注册业务的实现");
    }
}


