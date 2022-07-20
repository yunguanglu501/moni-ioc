package com.zen.service;

import com.zen.entity.Book;
import com.zen.entity.User;

public interface BookService {
    void  borrow(User user, Book book);
}

