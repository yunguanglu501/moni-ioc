package com.zen.dao;

import com.zen.annoation.Bean;
import com.zen.entity.Book;
import com.zen.entity.User;

import java.util.List;
@Bean
public class BookDaoImpl implements BookDao {
    @Override
    public User findBookById() {
        System.out.println("这里是BookDao-findBookById");
        return null;
    }

    @Override
    public List<User> findAllBook() {
        System.out.println("这里是BookDao-findAllBook");
        return null;
    }

    @Override
    public List<User> findBookByBookName(String username) {
        System.out.println("这里是BookDao-findBookByBookName");
        return null;
    }

    @Override
    public void save(Book book) {
        System.out.println("这里是BookDao-save");

    }
}


