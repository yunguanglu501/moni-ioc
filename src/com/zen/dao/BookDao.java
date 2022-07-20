package com.zen.dao;

import com.zen.entity.Book;
import com.zen.entity.User;

import java.util.List;

public interface BookDao {
    User findBookById();
    List<User> findAllBook();
    List<User>findBookByBookName(String username);
    void save(Book book);
}


