package com.zen.service;

import com.zen.annoation.AutoWired;
import com.zen.dao.BookDao;
import com.zen.dao.UserDao;
import com.zen.entity.Book;
import com.zen.entity.User;

public class BookServiceImpl implements BookService {
    @AutoWired
    private UserDao userDao;
    @AutoWired
    private BookDao bookDao;
    @Override
    public void borrow(User user,Book book) {
        userDao.save(user);
        System.out.println(user.getUsername()+"借了"+book.getName());
    }
}

